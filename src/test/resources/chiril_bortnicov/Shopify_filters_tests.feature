Feature: Color Filter Tests

  Background: Open Page
    Given Shopify Page is Open


  Scenario Outline: Black color test
    When Select color "<color>"
    Then Verify color is "<color>"
    Examples:
      | color |
      | Black |
      | Blue  |
      | Red   |

  Scenario Outline: Filter size test
    When Select size "<size>"
    Then Verify size is "<size>"
    Examples:
      | size |
      | S |
      | M |
      | L |
      | XL |

  Scenario Outline: Filter gender test
    When Select gender "<gender>"
    Then Verify gender is "<gender>"
    Examples:
      | gender |
      | Male |
      | Female |

