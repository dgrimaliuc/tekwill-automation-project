Feature: Create a scenario with next steps

  Scenario: Find average number

    Given three numbers "5", "4" and "6"
    When    find average of three numbers
    Then the average should be  "5"


    # 1. Matching Digits: Write a regex pattern that matches any sequence of digits in a given string.
    # Test it with various inputs, such as "Hello123", "456", and "abc789xyz".

  # Hello123, 456, abc789xyz / [1-9], [123|456|789]

  #2.	Matching Words: Create a regex pattern that matches any word in a sentence. Test it with different input sentences,
  # such as "This is a sample sentence." and "Regex patterns are powerful tools."

  # This|sample|sentence|powerful|tools

  #3.	Matching only three letters: Create a regex pattern  that matches
  # only three letters "h", "r" and "f", pattern should find all combination of h r and f letter (hrf, rfh, fhr etc)

  #[h,r,f] / [hrf]
