
Feature: As a data consumer, I want UI and DB book categories are match.

  @wip @db @ui
  Scenario: verify book categories with DB
    Given the "librarian" on the home page_os
    When the user navigates to "Books" page_os
    And the user clicks book categories_os
    Then verify book categories must match book_categories table from db_os