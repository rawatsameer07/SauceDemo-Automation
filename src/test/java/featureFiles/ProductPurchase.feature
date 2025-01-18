Feature: Purchase Product with Standard User

@smoke
Scenario Outline: Verify product is checkout successfully with a valid standard user.
    Given I launch the application "<--config.props/url"
    When I login as "<--config.props/standardUser" with password "<--config.props/password"
    Then I should be logged in successfully
  
  	When I sort items by "<sortProducts>"
  	And I add "<Items>" items priced at "<Price>" to the cart
  	Then I confirm "<Items>" items are added to the cart
  	And I proceed to checkout "<Items>" items
  	Then I fill user information "<firstName>" "<lastName>" "<postalCode>"
  	Then I complete the purchase of "<Items>" items
  	Examples:
  	|sortProducts       |Price | Items |firstName|lastName|postalCode|
  	|Price (high to low)|15.99 | 2		 |David    | Miller |   10400  |