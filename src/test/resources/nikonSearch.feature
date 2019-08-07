@run
Feature: nikonSearch

  Scenario Outline: when user is searching for content, then Nikon D3X should be in result
    Given a "https://www.amazon.com/" website with <browser>
    When User searches for "Nikon"
    And User sorts result from highest price to slowest
    And User clicks second result
    Then topic contains "Nikon D3X"

    Examples:
        | browser |
        | "firefox" |
        | "chrome" |