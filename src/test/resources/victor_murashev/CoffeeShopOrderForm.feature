#npx cucumber-js --tags "@orderForm"
@runAllTests @orderForm
Feature: Order Form Submission

  Scenario Outline: Fill the form and calculate the price
    Given the user is on the "https://cofeehouse.web.app/"
    When the user clicks the "Buy now" button to open order
    And the user fills in the "Name" field with "<name>"
    And the user fills in the "Phone" field with "<phone>"
    And the user selects "<coffeeType>" and sets the quantity to "<quantity>"
    And the user clicks the "Calculate" button to calculate order
    Then the displayed price should be: "<totalAmount>" Lei
    #And dummy end line

    Examples:
      | name    | phone         | coffeeType | quantity | totalAmount |
      | Victor  | +373 69212345 | Espresso   |        2 |          50 |
      | Evgenii | +373 69255321 | Americano  |        2 |          66 |
      | Uliana  | +373 69267890 | Cappuccino |        2 |          70 |
      | Irina   | +373 69212332 | Latte      |        2 |          76 |
      | Igori   | +373 69265356 | Lungo      |        2 |          74 |
      | Nikolai | +373 69212789 | Macchiato  |        2 |          84 |
