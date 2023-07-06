Feature: Location text check

  Background: Open Page
    Given Adopt Page with custom location is open

  Scenario Outline: Verify location text
    When "<my_location>" text is present in [Text Input], [Pets in ..] and [Adoptions in ..]

    Examples:
      | my_location |
      | Boston      |


  Scenario Outline: Verify [The Game] section text
    When Verify current section contains default info in [The game] section "<info1>" , "<info2>" , "<info3>" , "<info4>"

    Examples:
      | info1    | info2                 | info3                         | info4                                    |
      | The game | WebSocket messages: 1 | No pets. Go rescue some pets! | No adoptions. Go get those pets adopted! |









