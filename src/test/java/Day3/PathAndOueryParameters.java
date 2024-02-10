package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndOueryParameters {
	
	//Path paremeter we have to define using .pathParam("variable", "pathVAlue") and use it in url
	//Whereas query parameter we need to define using .queryParam("varibaleName", "value") and no need to use explicitly, it will be taken automatically once defined
	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	public void queyAndPathParameter() {
		
		given()
		.pathParam("MyPath", "users") //Path Parameter
		.queryParam("page", 2)   //Query Parameter
		.queryParam("id", 5)     //Query Parameter
		
		.when()
		.get("https://reqres.in/api/{MyPath}")
		
		.then()
		.statusCode(200)
		.log().all();
		
		
		
	}

}
