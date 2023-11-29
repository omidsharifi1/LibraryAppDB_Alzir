package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02StepDefs_nancy {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBookNumber;

//    @Given("the {string} on the home page")
//    public void the_on_the_home_page(String userType) {
//        loginPage.login(userType);
//    }
//
//    @When("the librarian gets borrowed books number")
//    public void the_librarian_gets_borrowed_books_number() {
//        BrowserUtil.waitFor(2);
//       actualBorrowedBookNumber = dashBoardPage.borrowedBooksNumber.getText();
//
//    }
//    @Then("borrowed books number information must match with DB")
//    public void borrowed_books_number_information_must_match_with_db() {
//        String query = "select count(id) from book_borrow where is_returned = 0";
//        DB_Util.runQuery(query);
//
//        String expectedBorrowedBookNumber = DB_Util.getFirstRowFirstColumn();
//
//        Assert.assertEquals(expectedBorrowedBookNumber,actualBorrowedBookNumber);
//    }



    @Given("the {string} on the home page_np")
    public void theOnTheHomePage_np(String userType) {
    loginPage.login(userType);
    BrowserUtil.waitFor(3);
    }

    @When("the librarian gets borrowed books number_np")
    public void theLibrarianGetsBorrowedBooksNumber_np() {
// OPT1
     actualBorrowedBookNumber = dashBoardPage.borrowedBooksNumber.getText();
// OP2:
        System.out.println("dashBoardPage.getModuleCount(\"Borrowed Books\") = " + dashBoardPage.getModuleCount("Borrowed Books"));
    }

    @Then("borrowed books number information must match with DB_np")
    public void borrowedBooksNumberInformationMustMatchWithDB_np() {
    String query = "select count(*) from book_borrow where returned_date is null";
    DB_Util.runQuery(query);

    String expectedBorrowedBookNumber = DB_Util.getFirstRowFirstColumn();

    Assert.assertEquals(expectedBorrowedBookNumber,actualBorrowedBookNumber);

    }
}
