
Feature: As a librarian, I want to know borrowed books number
  @wip @db @ui
  Scenario: verify the total amount of borrowed books
    Given the "librarian" on the home page_os
    When the librarian gets borrowed books number_os
    Then borrowed books number information must match with DB_os