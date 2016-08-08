package com.mengli.apitest.res;

import org.junit.Test;
import org.w3c.dom.events.EventException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

/**
 * Created by mlhuang on 8/2/16.
 */
public class Responsetest {
    @Test
    public void testGetAllResponse() throws EventException {
        String response = given().when().get("https://developers.google.com/books/docs/v1/reference/volumes/list").asString();
        System.out.println(response);
    }

    @Test
    public void testResponseBasicUsage() throws EventException {
        given()
                .when()
                .get("https://www.googleapis.com/books/v1/volumes?q=cucumber&maxResults=2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("kind", equalTo("books#volumes"))
                .body("items.id", hasItem("0dge3Xh6EjUC"))
                .body("items.volumeInfo.title", hasItems("Advances in Sea Cucumber Aquaculture and Management", "The striped cucumber beetle and its control"));
    }

    @Test
    public void testStaffJsonData() {
        String id = "111";
        given()
                .when()
                .get("http://localhost:7001/test/json/" + id)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("staffNo", equalTo(id))
                .body("name", equalTo("test"));
    }
}
