Feature: The first step in Cucumber

  Scenario: Average of three numbers
    Given I have three numbers "5", "4" and "6"
    When I find the average of three numbers
    Then The average is "5"



    #1. Matching Digits: Write a regex pattern that matches any sequence of digits in a given string.
    # Test it with various inputs, such as "Hello123", "456", and "abc789xyz".

  #  Regex pattern:
  # d+
  # [0-9]+ or [0-9]{1,100}


  #2. Matching Words: Create a regex pattern that matches any word in a sentence.
  #Test it with different input sentences, such as "This is a sample sentence."
  # and "Regex patterns are powerful tools."

  # Regex pattern:
  #[a-zA-Z]+ or [a-zA-Z]{1,100}


  #3. Matching only three letters: Create a regex pattern that matches only three letters
  # "h", "r" and "f", pattern should find all combination of h r and f letter (hrf, rfh, fhr etc)

  # Regex pattern:
  #[hrf]{3}