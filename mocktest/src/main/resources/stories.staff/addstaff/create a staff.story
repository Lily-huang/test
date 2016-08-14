Meta:

Narrative:
I want to add a staff

Scenario: scenario description
Given the following staffs info
      | name | age | id    |
      | Lily | 20  | 12345 |
When a request is made to add the staff
Then the info output should be "<output>"
    Examples:
      | output |
      | Lily   |