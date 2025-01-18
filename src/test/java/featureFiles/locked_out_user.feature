Feature: Validate the "locked_out_user" error message.

@smoke
Scenario Outline: Verify the locked_out_user error message is displayed correctly.
    Given I launch the application "<--config.props/url"
    When I login as "<--config.props/lockedUser" with password "<--config.props/password"
    Then I verify the error message "<message>" displayed for locked out user
    Examples:
    |message|
    |Epic sadface: Sorry, this user has been locked out.|
  