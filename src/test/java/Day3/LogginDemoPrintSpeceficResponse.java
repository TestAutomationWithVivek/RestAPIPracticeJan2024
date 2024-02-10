package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LogginDemoPrintSpeceficResponse {
	
	@Test()
	public void printSpeceficResponse() {
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		//if you want to print whole response
		//.log().all();
		
		//if you want only response (no headers and cookies)
		//.log().body();
		
		//if you want only cookies
		//.log().cookies();
		
		//if you want only headers
		.log().headers();
	}

}
