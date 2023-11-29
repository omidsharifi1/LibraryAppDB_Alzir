
Feature: As a data consumer, I want UI and DB book information are match.
  @wip @db @ui
  Scenario: Verify book information with DB
    Given the "librarian" on the home page_os
    And the user navigates to "Books" page_os
    When the user searches for "clean code omid" book_os
    And  the user clicks edit book button_os
    Then book information must match the Database_os