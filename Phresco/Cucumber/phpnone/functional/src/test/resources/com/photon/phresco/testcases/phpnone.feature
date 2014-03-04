Feature: Verifying PHP application	
As a PHP Helloworld project,
It will validate all scenarios.

Scenario: Checking Php None functionality 
Given users calling browser
Then the page title returned should be "helloworld"
And the page title returned should not be "SDR?w345dsfds"