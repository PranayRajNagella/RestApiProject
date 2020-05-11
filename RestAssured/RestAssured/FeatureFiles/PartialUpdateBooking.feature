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
Feature: Partial Update Booking

  @PartialUpdateBooking
	Scenario Outline: verify the user Partial booking flow is working as Expected
    Given when user hit the given uri for Paratial Booking
    When  user calls the partial update booking api with valid input from "TestData" and "<TestCaseName>"
    Then  Api call is success with status following Status Code <status>
    And   verify the partial booking response against the get booking response 
    
    Examples: 
   |TestCaseName    | status | 
   |PartialUpdate_1	|200   |   
   |PartialUpdate_2	|200   |  
