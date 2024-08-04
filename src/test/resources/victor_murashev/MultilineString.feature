Feature: Multiline String (Paragraph)

  Scenario: Count words in a paragraph
    Given I have the following paragraph:
      """
      This is a test paragraph. It has several sentences.
      Each sentence has multiple words.
      """
    When I count the length of the string
    Then the length should be 85
