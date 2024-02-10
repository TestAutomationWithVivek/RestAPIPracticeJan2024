package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
Different ways to create POST request body
1) Post request body using Hashmap
2) Post request body creation using using Org.JSON
3) post body creation using POJO class
4) Post request using external json file data
*/

public class WaysToCreatePostRequestBody {
	
	//1) Post request body using Hashmap
	//@Test(priority = 1)
	public void postRequestBodyUsingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "vivek");
		data.put("location", "pune");
		data.put("phone", "0000000");
		String [] coursesArray = {"c", "c++"};
		data.put( "courses", coursesArray);
		
		given()
		.contentType("application/json")
		.body(data)
		
	.when()
		.post("http://localhost:3000/students")
	
	.then()
		.statusCode(201)
		.body("name",equalTo("vivek"))
		.body("location", equalTo("pune"))
		.body("phone", equalTo("0000000"))
		.body("courses[0]",equalTo("c"))
		.body("courses[1]",equalTo("c++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
		
	}
	
	//2) Post request body creation using using Org.JSON
	//@Test(priority = 1)
	public void postRequestBodyJsonLibrary() {
		
		JSONObject data=new JSONObject();
		
		data.put("name", "vivek");
		data.put("location", "pune");
		data.put("phone", "0000000");
		String [] coursesArray = {"c", "c++"};
		data.put( "courses", coursesArray);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
	.when()
		.post("http://localhost:3000/students")
	
	.then()
		.statusCode(201)
		.body("name",equalTo("vivek"))
		.body("location", equalTo("pune"))
		.body("phone", equalTo("0000000"))
		.body("courses[0]",equalTo("c"))
		.body("courses[1]",equalTo("c++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
		
	}
	
	//3) post body creation using POJO class
	//@Test(priority = 1)
	public void postRequestBodyUsingPOJOClass() {
		
		POJO_Class_For_PostRequest data = new POJO_Class_For_PostRequest();
		
		data.setName("vivek");
		data.setLocation("pune");
		data.setPhone("0000000");
		String [] coursesArray = {"c", "c++"};
		data.setCourses(coursesArray);
		
		given()
		.contentType("application/json")
		.body(data)
		
	.when()
		.post("http://localhost:3000/students")
	
	.then()
		.statusCode(201)
		.body("name",equalTo("vivek"))
		.body("location", equalTo("pune"))
		.body("phone", equalTo("0000000"))
		.body("courses[0]",equalTo("c"))
		.body("courses[1]",equalTo("c++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
		
	}
	
	//4) Post request using external json file data
	@Test(priority = 1)
	public void postRequestBodyUsingExternalFile() throws FileNotFoundException {
		
		File filePath = new File(".\\ExternalJSonFilesForProject\\External-JSon-File.txt");
		FileReader fr =  new FileReader(filePath);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
	.when()
		.post("http://localhost:3000/students")
	
	.then()
		.statusCode(201)
		.body("name",equalTo("vivek"))
		.body("location", equalTo("pune"))
		.body("phone", equalTo("0000000"))
		.body("courses[0]",equalTo("c"))
		.body("courses[1]",equalTo("c++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
		
	}
	
	@Test(priority = 2)
	public void deletePostRequestBodyCreatedByHashMap() {
		
		given()
		
		.when()
		//@Here hardcoding the deleting id as we are having 4 records in file and it is just for the sake of practice
		.delete("http://localhost:3000/students/4")
		
		.then()
		.statusCode(200);
	}

}
