Feature: Books module
  As a librarian, I should be able to add new book into library

  @wip @db @ui
  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page_os
    And the user navigates to "Books" page_os
    When the librarian click to add book_os
    And the librarian enter book name "<Book Name>"_os
    When the librarian enter ISBN "<ISBN>"_os
    And the librarian enter year "<Year>"_os
    When the librarian enter author "<Author>"_os
    And the librarian choose the book category "<Book Category>"_os
    And the librarian click to save changes_os
    Then verify "The book has been created" message is displayed_os
    And verify "<Book Name>" information must match with DB_os
    Examples:
      | Book Name                  | ISBN     | Year | Author       | Book Category        |
      | Head First Java Omid       | 00112023 | 1999 | Kathy Smith  | Action and Adventure |
      | The Scrum Field Guide Omid | 00012023 | 2000 | Morgan Lacey | Short Story          |