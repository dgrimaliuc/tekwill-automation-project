Feature: The first automation tests

  Scenario: Average of three numbers

    Given I have three numbers "5", "4" and "6"
    When I find average of three numbers
    Then the average should be "5"

    # 1: Matching Digits: Write a regex pattern that matches any sequence of digits in a given string.
    #  Test it with various inputs, such as "Hello123", "456", and "abc789xyz".

  # Result:
  # [231|654|879]
  # [0-9]{1,20}

  # 2: Matching Words: Create a regex pattern that matches any word in a sentence.
  # Test it with different input sentences, such as "This is a sample sentence." and "Regex patterns are powerful tools."

  # Result:
  # [a-zA-z]{1,20}
  #(This|patterns)
  # .sample or .Regex

  # 3: Matching only three letters: Create a regex pattern  that matches only three letters "h", "r" and "f",
  # pattern should find all combination of h r and f letter (hrf, rfh, fhr etc)

  # Result:
  # [hrf]{3}