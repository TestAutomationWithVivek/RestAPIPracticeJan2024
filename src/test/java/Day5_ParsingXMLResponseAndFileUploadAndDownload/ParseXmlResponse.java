package Day5_ParsingXMLResponseAndFileUploadAndDownload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParseXmlResponse {
	
	//Approach1
	//@Test()
	public void parseXMLResponse() {
		given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
		.statusCode(200)
		.headers("Content-Type","application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page", equalTo("1"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		
	}
	
	//approach2
	//@Test()
	public void parseXMLResponse1() {
		Response response = given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/xml; charset=utf-8");
		String pageNumber = response.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNumber, "1");
		String name = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(name, "Developer");
			
	}
	
	//approach 3
	@Test()
	public void parseXMLResponse2() {
		Response response = given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlData = new XmlPath(response.asString());
		
		//verify total number of travellers size 
		List<String> travelers = xmlData.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travelers.size(), 10);
		
		//verify traveller name present in the response
		boolean travelerNameCorrect = false;
		List<String> travelersNames = xmlData.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		for(String travelerName : travelersNames) {
			System.out.println(travelerName);
			if (travelerName.equals("Developer")) {
				travelerNameCorrect = true;
				break;
			}
		}
		
		Assert.assertEquals(travelerNameCorrect, true);
	}

}
