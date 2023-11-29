package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;


public class US07StepDefs_omid {

    BookPage bookPage = new BookPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    String expectedBook;

    int locatorIndex;


    @And("the user searches for following {string} book_os")
    public void theUserSearchesForFollowingBook_os(String expectedBook) {
        this.expectedBook = expectedBook;
        bookPage.search.sendKeys(expectedBook);
    }


    @When("the user clicks Borrow Book_os")
    public void theUserClicksBorrowBook_os() {

        bookPage.borrowBook(expectedBook).click();


    }

    @Then("verify that book is shown in {string} page_os")
    public void verifyThatBookIsShownInPage_os(String moduleName) {
        dashBoardPage.navigateModule(moduleName);

        String expectedBookName = expectedBook;
        String actualBookName = "";

        List<WebElement> allBorrowedBooksName = borrowedBooksPage.allBorrowedBooksName;

        for (WebElement each : allBorrowedBooksName) {

            String currentBookName = each.getText();

            if (currentBookName.equals(expectedBookName)){
                actualBookName= currentBookName;
            }
        }

        Assert.assertTrue(expectedBookName.equals(actualBookName));




    }

    @And("verify logged student has same book in database_os")
    public void verifyLoggedStudentHasSameBookInDatabase_os() {

        DB_Util.runQuery("select name from users u\n" +
                "join book_borrow bb on u.id = bb.user_id\n" +
                "join books b on bb.book_id = b.id\n" +
                "where name = '"+expectedBook+"';");


        String actualBookName = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBook, actualBookName);




    }


}
