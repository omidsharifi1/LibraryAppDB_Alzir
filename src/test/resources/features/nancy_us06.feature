  @ui @db
Feature: Books module
  As a librarian, I should be able to add new book into library

  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page_np
    And the user navigates to "Books" page_np
    When the librarian click to add book_np
    And the librarian enter book name "<Book Name>"_np
    When the librarian enter ISBN "<ISBN>"_np
    And the librarian enter year "<Year>"_np
    When the librarian enter author "<Author>"_np
    And the librarian choose the book category "<Book Category>"_np
    And the librarian click to save changes_np
    Then verify "The book has been created." message is displayed_np
    And verify "<Book Name>" information must match with DB_np
    Examples:
      | Book Name               | ISBN     | Year | Author       | Book Category        |
      | I love Java_Nancy       | 10112023 | 2021 | Kathy Sierra | Action and Adventure |
      | Good Team Player_N      | 11122023 | 2006 | Mitch Lacey  | Short Story          |
      | How To Be The Best QA_N | 11132023 | 2003 | Mitch Lacey  | Fairy Tale           |