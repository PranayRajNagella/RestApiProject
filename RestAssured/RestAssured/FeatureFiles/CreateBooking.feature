#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: CreateBooking

  @CreateBooking
	Scenario Outline: verify the user random Create booking flow is working as Expected
    Given when user hit the given uri
    When  user calls the booking api with random valid input
    Then  Api call is success with status following status code <status>
    Then  Response contains the expected text as "<ContainsText>"
    And   verify the ResponseStructure is as Expected
    
    Examples: 
    | status  | ContainsText|
   	|200      | OK   |
   	
    @CreateBooking
   	Scenario Outline: verify the user Create booking flow is working as Expected
    Given when user hit the given uri
    When  user calls the create booking api with the input from "TestData" and "<TestCaseName>"
    Then  Api call is success with status following status code <status>
    Then  Response contains the expected text as "<ContainsText>"
    And   verify the ResponseStructure is as Expected
      Examples: 
   |TestCaseName | status  | ContainsText|
   |CreateBooking_1	|200   | OK          |
	 |CreateBooking_3	|400   | Bad Request |	

