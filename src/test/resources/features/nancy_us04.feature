
 @ui @db
Feature: As a data consumer, I want UI and DB book information are match.

  Scenario: Verify book information with DB
    Given the "librarian" on the home page_np
    And the user navigates to "Books" page_np
    When the user searches for "Clean Code" book_np
    And  the user clicks edit book button_np
    Then book information must match the Database_np