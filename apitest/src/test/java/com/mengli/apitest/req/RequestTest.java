package com.mengli.apitest.req;

import org.junit.Test;
import org.w3c.dom.events.EventException;

import static io.restassured.RestAssured.given;

/**
 * Created by mlhuang on 8/1/16.
 */
public class RequestTest {
    @Test
    public void testGoogleBookSearch(){
        given()
                .when()
                .get("https://www.googleapis.com/books/v1/volumes?q=cucumber&maxResults=2")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testGoogleBookAPIDataInParameters() throws EventException {
        given().
                params("q","cucumber","maxResults","2").
                when()
                .get("https://www.googleapis.com/books/v1/volumes")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testStaffJson(){
        given()
                .when()
                .get("http://localhost:7001/test/json/111")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void addStaff(){
        String staff="{\"name\":\"testAdd\",\"staffNo\":\"133\",\"age\":24}";
        given()
                .body(staff)
                .when()
                .post("http://localhost:7001/staff/add")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
