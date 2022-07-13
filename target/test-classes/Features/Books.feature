Feature: Manage Books

	Can get the Book By ID.

	Scenario Outline: Get Book By ID
    Given Provide ID "<id>"
    When Send request to Get Book by ID
    Then Verify response 
    Examples: Get Book by ID
   	|id|
	|99|
	|100|
	|101|
	|102|