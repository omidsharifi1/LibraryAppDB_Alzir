package com.library.steps;


import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



public class US05StepDefs_omid {

    String actualPopularBook;

    @When("I execute query to find most popular book genre_os")
    public void iExecuteQueryToFindMostPopularBookGenre_os() {

        DB_Util.runQuery("select bc.name,count(*) from book_borrow bb\n" +
                "join books b on b.id = bb.book_id\n" +
                "join book_categories bc on bc.id = b.book_category_id\n" +
                "group by bc.name\n" +
                "order by 2 desc;");

        actualPopularBook = DB_Util.getFirstRowFirstColumn();


        //  System.out.println("actualPopularBook = " + actualPopularBook);
    }

    @Then("verify {string} is the most popular book genre_os")
    public void verifyIsTheMostPopularBookGenre_os(String expectedPopularBook) {

        Assert.assertEquals(expectedPopularBook, actualPopularBook);

    }
}
