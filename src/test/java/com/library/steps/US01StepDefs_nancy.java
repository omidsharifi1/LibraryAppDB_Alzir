package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US01StepDefs_nancy {
    // The first scenario of US1 is covered in UserStepDefs which is came with project template

    List<String> actualAllColumnNameAsList;

    String actualUserCount;

    @Given("Establish the database connection_np")
    public void establish_the_database_connection_np() {
        //Make DB Connection
        // DB_Util.createConnection();

        System.out.println("------------------------------------------");
        System.out.println("-----DB CONNECTION IS DONE BY HOOKS -----");
        System.out.println("------------------------------------------");


    }
    @When("Execute query to get all IDs from users_np")
    public void execute_query_to_get_all_i_ds_from_users_np() {

        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);

    }
    @Then("verify all users has unique ID_np")
    public void verify_all_users_has_unique_id_np() {
        String query="select count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);

        // Close Coneection
        // DB_Util.destroy();
        System.out.println("------------------------------------------");
        System.out.println("-----DB CONNECTION IS CLOSED BY HOOKS -----");
        System.out.println("------------------------------------------");
    }




    @When("Execute query to get all columns_np")
    public void execute_query_to_get_all_columns_np() {
        String query = "select * from users";
        DB_Util.runQuery(query);

       actualAllColumnNameAsList = DB_Util.getAllColumnNamesAsList();
    }


    @Then("verify the below columns are listed in result_np")
    public void verifyTheBelowColumnsAreListedInResult_np(List<String> expectedAllColumnNameAsList) {

        Assert.assertEquals(expectedAllColumnNameAsList,actualAllColumnNameAsList);
        System.out.println("Test pass!!!");

    }
}
