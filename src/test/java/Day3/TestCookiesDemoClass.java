package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestCookiesDemoClass {
	
	@Test(priority = 1)
	public void testCookies() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		//.cookie("AEC", "Ae3NU9OsunTVSNaOUZTiJR8i3vIKf96ey1dnJRoJkY-u_PIkzd02LuGddw")
		.cookie("AEC")
		.log().all();
	}
	
	@Test(priority = 2)
	public void getCookiesInfo() {
		
		Response response = given()
		
		.when()
		.get("https://www.google.com/");
		
		//Get single cookie information
		//String valueOfSingleCookie = response.getCookie("AEC");
		//System.out.println("Value of single cookies AEC = " + valueOfSingleCookie);
		
		//Get All Cookies Info
		Map<String, String> allCookies =  response.getCookies();
		//System.out.println(allCookies.keySet());
		Set<Entry<String, String>> entrySet = allCookies.entrySet();
		Set<String> allKeys = allCookies.keySet();
	
		Iterator itr = allKeys.iterator();
		while(itr.hasNext()) {
			//System.out.println("All  cookies key-value pairs  ==>> "+ itr.next());
			String eachKey = (String) itr.next();
			System.out.println(" cookie key " + eachKey + " ==>> " + " cookie value " + response.getCookie(eachKey));
		}
																											
	}
	
	

}
