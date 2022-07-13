package com.serene.tests.features.steps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import net.serenitybdd.junit.runners.SerenityRunner;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.After;

@RunWith(SerenityRunner.class)
public class TC1_GetListBooks {
	
	Books books = new Books();
	private SoftAssertions softAssertions;
	
	@Before
    public void init() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Books";
        softAssertions = new SoftAssertions();
    }
	
	@Test
	public void API1_GetListBooks()
	{
		Response res = books.getListBooks();
		JsonPath jsonpath = res.jsonPath();
		softAssertions.assertThat(res.getStatusCode()).isEqualTo(200);
		softAssertions.assertThat(books.isTitleBookExist(jsonpath, "Book 198")).isEqualTo(true);
		softAssertions.assertThat(books.isTitleBookExist(jsonpath, "Book 200")).isEqualTo(false);
		softAssertions.assertThat(books.isTitleBookExist(jsonpath, "Book 500")).isEqualTo(true);
		softAssertions.assertThat(books.getListBookCount(jsonpath)).isEqualTo(200);
	}
	
	@After
	 public void cleanup() {
		softAssertions.assertAll();
        RestAssured.reset();
    }
}
