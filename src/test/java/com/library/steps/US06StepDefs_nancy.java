package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US06StepDefs_nancy {

    BookPage bookPage = new BookPage();

    @When("the librarian click to add book_np")
    public void the_librarian_click_to_add_book_np() {
        BrowserUtil.waitForClickablility(bookPage.addBook,5).click();

    }

    @And("the librarian enter book name {string}_np")
    public void theLibrarianEnterBookName_np(String bookName) {
    BrowserUtil.waitForVisibility(bookPage.bookName,5).sendKeys(bookName );
    }

    @When("the librarian enter ISBN {string}_np")
    public void theLibrarianEnterISBN_np(String isbn) {
        BrowserUtil.waitForVisibility(bookPage.isbn,5).sendKeys(isbn );

    }

    @And("the librarian enter year {string}_np")
    public void theLibrarianEnterYear_np(String year) {
    BrowserUtil.waitForVisibility(bookPage.year,5).sendKeys(year);

    }

    @When("the librarian enter author {string}_np")
    public void theLibrarianEnterAuthor_np(String author) {
        BrowserUtil.waitForVisibility(bookPage.author,5).sendKeys(author );

    }

    @And("the librarian choose the book category {string}_np")
    public void theLibrarianChooseTheBookCategory_np(String bookCategory) {
    BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown,bookCategory);

    }

    @And("the librarian click to save changes_np")
    public void theLibrarianClickToSaveChanges_np() {
        BrowserUtil.waitForClickablility(bookPage.saveChanges,5).click();
        BrowserUtil.waitFor(2);
    }

    @Then("verify {string} message is displayed_np")
    public void verifyMessageIsDisplayed_np(String expectedMessage) {
    String actualMessage = bookPage.toastMessage.getText();

    Assert.assertEquals(expectedMessage,actualMessage);

    }

    @And("verify {string} information must match with DB_np")
    public void verifyInformationMustMatchWithDB_np(String expectedBookName) {
        BrowserUtil.waitForClickablility(bookPage.search,5).sendKeys(expectedBookName + Keys.ENTER);

       BrowserUtil.waitForVisibility(bookPage.editBook(expectedBookName),5).click();

       BrowserUtil.waitFor(2);

        List<String> expectedBookInfoAsList = new ArrayList<>();

        BrowserUtil.waitForVisibility(bookPage.categoryDropdown,5);

        BrowserUtil.waitFor(5);

        expectedBookInfoAsList.addAll(Arrays.asList(
                bookPage.isbn.getAttribute("value") ,
                bookPage.bookName.getAttribute("value") ,
                bookPage.author.getAttribute("value") ,
                BrowserUtil.getTextSelectedOptionDropdown(bookPage.categoryDropdown),
                bookPage.year.getAttribute("value")

        ));
        System.out.println("expectedBookInfoAsList: "+expectedBookInfoAsList);

        String query = "select isbn,B.name,author,BC.name, year\n" +
                "from books B\n" +
                "join book_categories BC on B.book_category_id = BC.id\n" +
                "where B.name = '"+ expectedBookName +"'";
        DB_Util.runQuery(query);

        BrowserUtil.waitFor(3);
        List<String> actualBookInfoAsList = DB_Util.getRowDataAsList(1);

        Assert.assertEquals(expectedBookInfoAsList,actualBookInfoAsList);


    }
}
