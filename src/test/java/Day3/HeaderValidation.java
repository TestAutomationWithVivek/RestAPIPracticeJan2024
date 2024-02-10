package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderValidation {
	
	//@Test(priority = 1)
	public void getHeader() {
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Content-Encoding", "gzip")
		.header("Server", "gws");
	}
	
	@Test(priority = 2)
	public void getAndPrintHeader() {
         Response response = given()
		
		.when()
		.get("https://www.google.com/");
		
		//String header = response.getHeader("Content-Type");
        //System.out.println("Printing a header Content-Type " + header);
        
        Headers headers = response.getHeaders();
        for(Header head : headers) {
        	System.out.println("Printing each Header ..>>>>>>>>>>>> " +" Header Name < " +head.getName()+ " > and < " + head.getValue() + " > header value" );
        }
	}

}
