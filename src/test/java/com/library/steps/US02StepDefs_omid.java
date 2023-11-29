package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



public class US02StepDefs_omid {

    String actualBookNumber;
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();


    @Given("the {string} on the home page_os")
    public void theOnTheHomePage_os(String userType) {
        loginPage.login(userType);
    }

    @When("the librarian gets borrowed books number_os")
    public void theLibrarianGetsBorrowedBooksNumber_os() {

        BrowserUtil.waitForVisibility(dashBoardPage.borrowedBooksNumber, 10);
        actualBookNumber = dashBoardPage.getModuleCount("Borrowed Books");

        //      System.out.println("actualBookNumber = " + actualBookNumber);
    }

    @Then("borrowed books number information must match with DB_os")
    public void borrowedBooksNumberInformationMustMatchWithDB_os() {

        DB_Util.runQuery("SELECT count(*) as borrowedBooks from book_borrow\n" +
                "where is_returned = 0;");

        String expectedBookNumber = DB_Util.getFirstRowFirstColumn();
        //   System.out.println("expectedBookNumber = " + expectedBookNumber);

        Assert.assertEquals(expectedBookNumber, actualBookNumber);
    }
}
