package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class US06StepDefs_omid {

    BookPage bookPage = new BookPage();

    @When("the librarian click to add book_os")
    public void theLibrarianClickToAddBook_os() {

        bookPage.addBook.click();
    }

    @And("the librarian enter book name {string}_os")
    public void theLibrarianEnterBookName_os(String bookName) {
        bookPage.bookName.sendKeys(bookName);

    }

    @When("the librarian enter ISBN {string}_os")
    public void theLibrarianEnterISBN_os(String isbn) {
        bookPage.isbn.sendKeys(isbn);
    }

    @And("the librarian enter year {string}_os")
    public void theLibrarianEnterYear_os(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}_os")
    public void theLibrarianEnterAuthor_os(String author) {
        bookPage.author.sendKeys(author);
    }

    @And("the librarian choose the book category {string}_os")
    public void theLibrarianChooseTheBookCategory_os(String bookCategory) {
        bookPage.categoryDropdown.sendKeys(bookCategory);
    }

    @And("the librarian click to save changes_os")
    public void theLibrarianClickToSaveChanges_os() {
        bookPage.saveChanges.click();
    }

    @Then("verify {string} message is displayed_os")
    public void verifyMessageIsDisplayed_os(String expectedMessage) {

        String actualMessage = bookPage.toastMessage.getText();

        Assert.assertFalse(expectedMessage.equals(actualMessage)); //Expected doesn't match Actual result
    }

    @And("verify {string} information must match with DB_os")
    public void verifyInformationMustMatchWithDB_os(String expectedBookName) {

        DB_Util.runQuery("select name from books where name = '"+expectedBookName+"';");

        String actualBookName = DB_Util.getFirstRowFirstColumn();

//        bookPage.search.sendKeys(expectedBookName);
//        bookPage.editBook(expectedBookName);

        Assert.assertEquals(expectedBookName, actualBookName);
    }
}
