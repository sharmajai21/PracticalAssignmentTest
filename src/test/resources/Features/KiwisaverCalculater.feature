@KiwisaverCalculator
Feature: Kiwisaver scheme retirement calculator

  Scenario: User clicks on help icon next to Current Age field on KiwiSaver Retirement Calculator
    Given User is on the Westpac bank website home page
    And Select KiwiSaver calculators menu option under KiwiSaver menu
    Then KiwiSaver calculators screen is displayed
    When User clicks on Click here to get started button on calculators page
    Then KiwiSaver Retirement Calculator screen is displayed
    When User click on information icon next to Current age field
    Then Age limit validation message is displayed

  Scenario Outline: Employed user calculates KiwiSaver Retirement Calculator calculates their projected balance at retirement
    Given User is on the Westpac bank website home page
    And Select KiwiSaver calculators menu option under KiwiSaver menu
    Then KiwiSaver calculators screen is displayed
    When User clicks on Click here to get started button on calculators page
    Then KiwiSaver Retirement Calculator screen is displayed
    When User specify  values for "<curr age>", "<emp status>", "<salary pa>", "<member contrib>" and "<risk prof>"
    Then View Your KiwiSaver retirement projections button becomes enabled
    When User clicks on View Your KiwiSaver retirement projections button
    Then Estimated projected kiwisaver balance at age of 65 years is dispalyed on the calculator screen

    Examples:
      | curr age | emp status    | salary pa | member contrib | current balance | voluntary contrib | Volun Freq  | risk prof | saving goal at retirement |
      | 30       | Employed      | $82000    | 4%             |                 |                   |             | Growth    |                           |


  Scenario Outline: Self-employed or Not employed user calculates KiwiSaver Retirement Calculator calculates their projected balance at retirement
    Given User is on the Westpac bank website home page
    And Select KiwiSaver calculators menu option under KiwiSaver menu
    Then KiwiSaver calculators screen is displayed
    When User clicks on Click here to get started button on calculators page
    Then KiwiSaver Retirement Calculator screen is displayed
    When User specify  values for "<curr age>", "<emp status>", "<current balance>", "<voluntary contrib>", "<Volun Cont Freq>", "<risk prof>" and "<saving goal at retirement>"
    Then View Your KiwiSaver retirement projections button becomes enabled
    When User clicks on View Your KiwiSaver retirement projections button
    Then Estimated projected kiwisaver balance at age of 65 years is dispalyed on the calculator screen

    Examples:
      | curr age | emp status    | salary pa | member contrib | current balance | voluntary contrib | Volun Cont Freq | risk prof | saving goal at retirement |
      | 45       | Self-employed |           |                | $100000         | $90               | Fortnightly     | Balanced  | $290000                   |
      | 55       | Not employed  |           |                | $140000         | $10               | Annually        | Balanced  | $200000                   |




