Feature: Simple UI test

  Scenario Outline: Open the PetStore web app
    Given Open the PetStore
    Then I see "<title>" in location input
    Then I see "<title>" Pet section title
    Then I see "<title>" in Adoptions Section title
    Then I click open new tab
    Examples:
      | title |
      | Plett |

  Scenario Outline: Add pets in PetStore web app
    Given Open the PetStore
    Given Change location on "<town>"
    Then Add pet in your town
    Then Adopt the selected pet
#    Then Approve your pet 🦮
    Examples:
      | pet  | town     |
      | Kiki | New York |

    #HomeWork
  Scenario: Verify text in "The game" section
    Given Open the PetStore in Chisinau
    Then Verify the Pets in Chisinau: 5
    Then Verify the Adoptions in Chisinau: 2
