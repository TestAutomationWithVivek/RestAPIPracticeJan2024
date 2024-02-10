package Day4_ParsingJSonResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ParsingJsonResponse {
	
	//approach1
	//@Test()
	public void parsingResponseWithSimpleApproach() {
		given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store")
		
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8")
		.body("book[1].title", equalTo("Sword of Honour"));
	}
	
	//approach2
	//@Test()
	public void parsingResponseWithSimpleApproach1() {
		Response response = given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookTitle = response.jsonPath().get("book[1].title").toString();
		Assert.assertEquals(bookTitle, "Sword of Honour");
	}
	
	//approach3
	@Test()
	public void parsingResponseWithSimpleApproach2() {
		Response response = given()
		.contentType(ContentType.JSON)

		.when()
		.get("http://localhost:3000/store");

		//use JSONObject
		//Converting response to Json object type : firstly response has to be converted to string 
		JSONObject jsonData = new JSONObject(response.asString());
		
		for(int i = 0; i < jsonData.getJSONArray("book").length(); i++) {
			String eachTitle = jsonData.getJSONArray("book").getJSONObject(i).getString("title").toString();
			System.out.println("Printing every book from the response = " + eachTitle);
		}
	}


}
