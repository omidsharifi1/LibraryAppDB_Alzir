

  @ui @db
Feature: As a data consumer, I want UI and DB book categories are match.

  Scenario: verify book categories with DB
    Given the "librarian" on the home page_np
    When the user navigates to "Books" page_np
    And the user clicks book categories_np
    Then verify book categories must match book_categories table from db_np