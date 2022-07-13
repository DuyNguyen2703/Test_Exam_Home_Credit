package com.serene.tests.features.steps;

import org.junit.runner.RunWith;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import io.restassured.RestAssured;

import net.thucydides.core.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class TC2_GetBookById {
	
	@Before
    public void init() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Books";
    }
	
	@Steps
	Books book;
	
	@Given("^Provide ID \"([^\"]*)\"$")
	public void buildGetBookById(String id){
	    book.buildGetBookById(id);
	}

	@When("^Send request to Get Book by ID$")
	public void sendGetBookById(){
	    book.sendGetBookById();
	}

	@Then("^Verify response$")
	public void verifyGetBookById(){
	    book.verifyGetBookById();
	}
	
	@After
	public void cleanup() {
		RestAssured.reset();
   }
}
