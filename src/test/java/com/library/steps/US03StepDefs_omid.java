package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03StepDefs_omid {
    List<String> actualBookCategories;
    BookPage bookPage = new BookPage();


    @When("the user navigates to {string} page_os")
    public void theUserNavigatesToPage_os(String page) {
        bookPage.navigateModule(page);
    }

    @And("the user clicks book categories_os")
    public void theUserClicksBookCategories_os() {

        actualBookCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualBookCategories.remove("ALL");

        //  System.out.println("actualBookCategories = " + actualBookCategories);
    }

    @Then("verify book categories must match book_categories table from db_os")
    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb_os() {

        DB_Util.runQuery("SELECT name from book_categories;");

        List<String> expectedBookCategories = DB_Util.getColumnDataAsList(1);
        //  System.out.println("expectedBookCategories = " + expectedBookCategories);
        Assert.assertEquals(expectedBookCategories, actualBookCategories);
    }
}
