Feature: OrangeHRM Website Login Scenario
Scenario: User should login with valid credentials
Given Browser is open
And User is in login page
When User enters valid username and password 
And Click Submit Button
Then User is in welcome page

Scenario: Select QA checkbox in Job 
Given User is in welcome page
When User clicks Admin options 
And Click Job Nav link
Then User clicks on QA Engineer

Scenario: CRUD Operations on REQRES


