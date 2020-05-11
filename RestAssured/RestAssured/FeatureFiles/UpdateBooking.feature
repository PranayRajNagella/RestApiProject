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
Feature: Update Booking
  I want to use this template for my feature file

  @UpdateBooking
	Scenario Outline: verify the user Update booking flow is working as Expected
    Given when user hit the given uri for Update Booking
    When  user calls the update booking api with valid input from "TestData" and "<TestCaseName>"
    Then  Api call is success with the following Status Code <status>
    And   verify the update booking response against the get booking
    
    Examples: 
   |TestCaseName | status  | 
   |Update_1	|200   |   
   |Update_2	|200   |  
   |Update_3  |400   |   
   |Update_5	| 400  | 
