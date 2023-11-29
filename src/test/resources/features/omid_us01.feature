Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.
  Background:
    Given Establish the database connection_os

  @wip @db @ui
  Scenario: verify users has unique IDs_os
    When Execute query to get all IDs from users_os
    Then verify all users has unique ID_os


  Scenario: verify users table columns
    When Execute query to get all columns_os
    Then verify the below columns are listed in result_os

      | id            |
      | full_name     |
      | email         |
      | password      |
      | user_group_id |
      | image         |
      | extra_data    |
      | status        |
      | is_admin      |
      | start_date    |
      | end_date      |
      | address       |
