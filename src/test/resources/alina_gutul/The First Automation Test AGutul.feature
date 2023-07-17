Feature: The First Automation Test AGutul

  Scenario: count average
    Given Given I have three numbers "5", "4" and "6" AG
    When I find avg of three numbers
    Then average should be "5"

    # Task 1
    # Matching Digits: Write a regex pattern that matches any sequence of digits in a given string.
    # Test it with various inputs, such as "Hello123", "456", and "abc789xyz".
    # Answer: ("\d+")

    # Task 2
    # Matching Words: Create a regex pattern that matches any word in a sentence.
    # Test it with different input sentences, such as "This is a sample sentence." and "Regex patterns are powerful tools."
    # Answer: ("\b\w+\b")

    # Task 3
    # Matching only three letters: Create a regex pattern that matches only three letters "h", "r" and "f",
    # pattern should find all combination of h r and f letter (hrf, rfh, fhr etc)
    # Answer: ("(h|r|f){3}")
