# Required software:
1.Maven (in my case 3.6.0 version)

2.Java Development Kit 8 

3.Google Chrome (in my case 76 version)

4.Firefox (in my case 69.0b11 version)
# Setup:
Download selenium drivers suitable for your Google Chrome and Firefox versions. Set up paths for the drivers and binary files of your browsers in selenium.properties file.
# Run:
To run tests open console (e.g. cmd) in project directory and write “mvn clean test” then press enter.
# Results:
Results of tests are available in console or in index.html file located in target/htmlreports subdirectory of the project.
# Description:
### pom.xml
This file tells which dependencies should be downloaded for the project to work properly and provides configuration for building the project.
### Runner.java
Annotations in Runner class provide application with informations that tests should be run with Cucumber, where features are located, implementation of those features and where test reports are saved.
### selenium.properties
This file was created to facilitate path configuration without a need to interfere into java code. I decided to add paths to binarys of browsers in case you would like to use specific versions or there would be problem with automatically finding those files.
### nikonSearch.feature 
Content of this file is responsible for creating the scenario and describing steps included in it.  Expressions in quotation marks are used as parameters in implementation of the scenario. Expression <browser> is connected to examples below scenario and allows to run scenario multiple times with different parameters. 
### NikonSearch.java
This file contains java implementation of the scenario. SetUp method allow us to retrieve paths to drivers from selenium.properties. Method with annotation @Given initializes different driver based on browser parameter and opens website. Methods with annotations @When and @Then are responsible for implementing rest of the steps of scenario. Finally there is cleanUp method that shuts down WebDriver. 
