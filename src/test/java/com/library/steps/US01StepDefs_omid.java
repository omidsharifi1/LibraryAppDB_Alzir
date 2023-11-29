package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;


import java.util.List;

public class US01StepDefs_omid {

    String actualIdCount;
    List<String> actualColumns;


    @Given("Establish the database connection_os")
    public void establishTheDatabaseConnection_os() {

    }


    @When("Execute query to get all IDs from users_os")
    public void executeQueryToGetAllIDsFromUsers_os() {

        DB_Util.runQuery("select count(id) from users;");
        actualIdCount = DB_Util.getFirstRowFirstColumn();

    }

    @Then("verify all users has unique ID_os")
    public void verifyAllUsersHasUniqueID_os() {

        DB_Util.runQuery("select count(distinct id) from users;");
        String expectedIdCount = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedIdCount, actualIdCount);
    }

    @When("Execute query to get all columns_os")
    public void executeQueryToGetAllColumns_os() {
        DB_Util.runQuery("select * from users;");

        actualColumns = DB_Util.getAllColumnNamesAsList();

    }

    @Then("verify the below columns are listed in result_os")
    public void verifyTheBelowColumnsAreListedInResult_os(List<String> expectedColumns) {

        Assert.assertEquals("Columns doesn't match",expectedColumns, actualColumns);

    }







}
