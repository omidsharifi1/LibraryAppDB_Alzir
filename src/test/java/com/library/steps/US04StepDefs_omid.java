package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class US04StepDefs_omid {

    String expectedBook;
    BookPage bookPage = new BookPage();


    @When("the user searches for {string} book_os")
    public void theUserSearchesForBook_os(String expectedBook) {
        this.expectedBook = expectedBook;
        bookPage.search.sendKeys(expectedBook);

    }

    @And("the user clicks edit book button_os")
    public void theUserClicksEditBookButton_os() {

        bookPage.editBook(expectedBook).click();

    }

    @Then("book information must match the Database_os")
    public void bookInformationMustMatchTheDatabase_os() {

        DB_Util.runQuery("select name, isbn, year, author,book_category_id, description from books where name = '"+expectedBook+"';");

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualIsbn = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualCategory = bookPage.categoryDropdown.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");


        String expectedBookName = DB_Util.getCellValue(1,1);
        String expectedIsbn = DB_Util.getCellValue(1,2);
        String expectedYear = DB_Util.getCellValue(1,3);
        String expectedAuthor = DB_Util.getCellValue(1,4);
        String expectedCategory = DB_Util.getCellValue(1,5);
        String expectedDescription = DB_Util.getCellValue(1,6);


        Assert.assertEquals(expectedBookName, actualBookName);
        Assert.assertEquals(expectedIsbn, actualIsbn);
        Assert.assertEquals(expectedYear, actualYear);
        Assert.assertEquals(expectedAuthor, actualAuthor);
        Assert.assertEquals(expectedCategory, actualCategory);
        Assert.assertEquals(expectedDescription, actualDescription);
    }

}
