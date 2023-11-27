@ui @db

Feature: Books module
  As a students, I should be able to borrow book

  Scenario: Student borrow new book
    Given the "student" on the home page_np
    And the user navigates to "Books" page_np
    And the user searches for "I love Java_Nancy" book_np
    When the user clicks Borrow Book_np
    Then verify that book is shown in "Borrowing Books" page_np
    And  verify logged student has same book in database_np