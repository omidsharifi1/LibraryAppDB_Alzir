package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05StepDefs_nancy {

    String actualGenreName;

    @When("I execute query to find most popular book genre_np")
    public void i_execute_query_to_find_most_popular_book_genre_np() {
        String query = "select BC.name , count(*)\n" +
                "from book_borrow BBR\n" +
                "join books B on BBR.book_id = B.id\n" +
                "join book_categories BC on B.book_category_id = BC.id\n" +
                "group by name\n" +
                "order by 2 desc";

        DB_Util.runQuery(query);

         actualGenreName = DB_Util.getFirstRowFirstColumn();

    }

    @Then("verify {string} is the most popular book genre._np")
    public void verifyIsTheMostPopularBookGenre_np(String expectedGenreName) {
        Assert.assertEquals(expectedGenreName,actualGenreName);


    }
}
