package com.serene.tests.features.steps;

import java.util.List;
import org.junit.Assert;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Books {

	private Response res = null;
    private RequestSpecification requestSpec;
     
    @Step
	public Response getListBooks() {
		return SerenityRest.given().log().all().when().get();
	}
	
    @Step
	public boolean isTitleBookExist(JsonPath jsonpath, String title) {
		List<String> allTitles = jsonpath.getList("title");
		for (int i = 0; i < allTitles.size(); i++) {
			if (allTitles.get(i).equals(title)) {
				return true;
			}
		}
		return false;
	}
	
    @Step
	public int getListBookCount(JsonPath jsonpath) {
		return jsonpath.getList("id").size();
	}
    
	@Step
	public void buildGetBookById(String id) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBasePath(id);
		builder.setContentType("application/json");
		requestSpec = builder.build();
		requestSpec = SerenityRest.given().spec(requestSpec);
		requestSpec.log().all();
	}
    
	@Step
	public void sendGetBookById() {
		res = requestSpec.when().get();
	}

	@Step
	public void verifyGetBookById() {
		Assert.assertEquals("Status Code Failed!", 200, res.getStatusCode());
		//res.then().body(matchesJsonSchemaInClasspath("src/test/resources/schema/SchemaBook.json"));
	}
}