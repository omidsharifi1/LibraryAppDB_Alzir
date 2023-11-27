package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03StepDefs_nancy {
    DashBoardPage dashBoardPage = new DashBoardPage();
    BookPage bookPage = new BookPage();
    List<String> actualCategory;

//    @When("the user navigates to {string} page")
//    public void the_user_navigates_to_page(String moduleName) {
//        dashBoardPage.navigateModule(moduleName);
//    }
//
//
//    @And("the user clicks book categories")
//    public void theUserClicksBookCategories() {
//        BrowserUtil.waitFor(3);
//    bookPage.mainCategoryElement.click();
//
//    }
//
//
//    @Then("verify book categories must match book_categories table from db")
//    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb() {
//
//    List<String> actualBookCategoriesAsList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
//    actualBookCategoriesAsList.remove(0);
//
//    String query = "select name from book_categories";
//    DB_Util.runQuery(query);
//
//     List<String> expectedBookCategoriesAsList = DB_Util.getColumnDataAsList(1);
//
//     Assert.assertEquals(expectedBookCategoriesAsList,actualBookCategoriesAsList);
//
//    }

    @When("the user navigates to {string} page_np")
    public void theUserNavigatesToPage_np(String moduleName) {
    dashBoardPage.navigateModule(moduleName);
        BrowserUtil.waitFor(2);
    }

    @And("the user clicks book categories_np")
    public void theUserClicksBookCategories_np() {
        // dont need to click here, bc this is DB testing
        bookPage.mainCategoryElement.click();
        BrowserUtil.waitFor(2);

         actualCategory = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategory.remove(0);
    }

    @Then("verify book categories must match book_categories table from db_np")
    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb_np() {

        String query = "select name from book_categories";
        DB_Util.runQuery(query);

        List<String> expectedCategory = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedCategory,actualCategory);

    }
}
