
Feature: Books module
  As a students, I should be able to borrow book
  @wip @db @ui
  Scenario: Student borrow new book
    Given the "student" on the home page_os
    And the user navigates to "Books" page_os
    And the user searches for following "The Scrum Field Guide Omid" book_os
    When the user clicks Borrow Book_os
    Then verify that book is shown in "Borrowing Books" page_os
    And  verify logged student has same book in database_os