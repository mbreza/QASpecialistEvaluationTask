package bindings;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mateusz Breza
 */
public class NikonSearch {

    private WebDriver driver;
    private Properties prop;

    @Before
    public void setUp() throws IOException {
        InputStream input = NikonSearch.class.getClassLoader().getResourceAsStream("selenium.properties");
        prop = new Properties();
        prop.load(input);
    }

    @Given("^a \"([^\"]*)\" website with \"([^\"]*)\"$")
    public void a_website(String url, String browser) {
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.firefox.bin", prop.getProperty("firefox.bin"));
            System.setProperty("webdriver.gecko.driver", prop.getProperty("firefox.driver"));
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary(prop.getProperty("chrome.bin"));
            System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.driver"));
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("^User searches for \"([^\"]*)\"$")
    public void user_searches_for(String searchedPhrase) {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchedPhrase);
        driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
    }

    @When("^User sorts result from highest price to slowest$")
    public void user_sorts_result_from_highest_price_to_slowest() {
        driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"s-result-sort-select_2\"]")).click();
    }

    @When("^User clicks second result$")
    public void user_clicks_second_result() {
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[3]/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div/h2/a/span")).click();
    }

    @Then("^topic contains \"([^\"]*)\"$")
    public void topic_contains(String topicContent) {
        assertTrue(driver.findElement(By.id("productTitle")).getText().contains(topicContent));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

}