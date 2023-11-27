package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US04StepDefs_nancy {
    BookPage bookPage = new BookPage();
    String bookName;

    @When("the user searches for {string} book_np")
    public void the_user_searches_for_book_np(String bookName) {
        BrowserUtil.waitForClickablility(bookPage.search,5).sendKeys(bookName);
        // assign this local bookName to class-level bookName to use for editBook method from BookPage
        this.bookName = bookName;
        System.out.println(bookName);
    }

    @And("the user clicks edit book button_np")
    public void theUserClicksEditBookButton_np() {
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName),5).click();

    }

    @Then("book information must match the Database_np")
    public void bookInformationMustMatchTheDatabase_np() {
        BrowserUtil.waitFor(3);
    List<String> actualBookRowAsList = new ArrayList<>();

    actualBookRowAsList.addAll(Arrays.asList(
            bookPage.bookName.getAttribute("value"),
            bookPage.isbn.getAttribute("value"),
            bookPage.year.getAttribute("value"),
            bookPage.author.getAttribute("value"),
            //it will get category_id
            //bookPage.categoryDropdown.getAttribute("value"),
                // still can use it here to get value of selected option Dropdown
            BrowserUtil.getTextSelectedOptionDropdown(bookPage.categoryDropdown),
            bookPage.description.getAttribute("value")
    ));
        System.out.println(actualBookRowAsList);
        BrowserUtil.waitFor(2);

        String query = "select b.name,isbn,year, author,bc.name,b.description\n" +
                "from books b join book_categories bc on b.book_category_id = bc.id where b.name = '" + bookName + "'order by isbn desc";
        DB_Util.runQuery(query);

        List<String> expectedBookRowAsList = DB_Util.getRowDataAsList(1);

        Assert.assertEquals(expectedBookRowAsList,actualBookRowAsList);
        System.out.println(expectedBookRowAsList);
    }
}
