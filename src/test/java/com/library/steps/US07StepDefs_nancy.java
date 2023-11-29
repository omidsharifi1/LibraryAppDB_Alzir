package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class US07StepDefs_nancy {

    BookPage bookPage = new BookPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    String expectedBookName = "I love Java_Nancy";

    @When("the user clicks Borrow Book_np")
    public void the_user_clicks_borrow_book_np() {

        BrowserUtil.waitFor(2);

        BrowserUtil.waitForVisibility(bookPage.borrowBook(expectedBookName),10).click();

    }

    @Then("verify that book is shown in {string} page_np")
    public void verifyThatBookIsShownInPage_np(String moduleName) {
    dashBoardPage.navigateModule(moduleName);
/* Option 1: bc when doing UI, the new borrowed book is add to the end
        String actualBookName = borrowedBooksPage.allBorrowedBooksName.get(borrowedBooksPage.allBorrowedBooksName.size()-1).getText();
*/
// Option 2:
        String actualBookName ="";

        for(WebElement eachBorrowedBook : borrowedBooksPage.allBorrowedBooksName){
            if(eachBorrowedBook.getText().equalsIgnoreCase(expectedBookName)){
               actualBookName = eachBorrowedBook.getText();
               break;
            }
        }
        System.out.println("actualBookName = " + actualBookName);
        Assert.assertEquals(expectedBookName,actualBookName);
    }
    @And("verify logged student has same book in database_np")
    public void verifyLoggedStudentHasSameBookInDatabase_np() {
    String query = "select name from users U\n" +
            "join book_borrow BB on U.id = BB.user_id\n" +
            "join books B on BB.book_id = B.id\n" +
            "where name = '" + expectedBookName + "'";
        DB_Util.runQuery(query);

    String actualBookNameInDB = DB_Util.getFirstRowFirstColumn();

    Assert.assertEquals(expectedBookName,actualBookNameInDB);



    }
}
