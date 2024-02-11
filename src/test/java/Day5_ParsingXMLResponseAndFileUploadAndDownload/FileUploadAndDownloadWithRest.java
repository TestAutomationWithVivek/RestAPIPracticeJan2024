package Day5_ParsingXMLResponseAndFileUploadAndDownload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;


public class FileUploadAndDownloadWithRest {
	
	@Test(priority = 1)
	public void fileUpload() {
		File myFile = new File(".\\ExternalJSonFilesForProject\\students.json");
		
		given()
		.multiPart("file", myFile)
		.contentType("multipart/form-data")
		
		.when()
		.post("http://localhost:8080/uploadFile")
		
		.then()
		.statusCode(200)
		.body("fileName", equalTo("students.json"))
		.log().all();
		
	}
	
	//@Test()
	public void multipleFilesUpload() {
		File myFile = new File(".\\ExternalJSonFilesForProject\\students.json");
		File myFile1 = new File(".\\ExternalJSonFilesForProject\\External-Json-File.txt");
		
		given()
		.multiPart("files", myFile)
		.multiPart("files", myFile1)
		.contentType("multipart/form-data")
		
		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
		.statusCode(200)
		.body("[0].fileName", equalTo("students.json"))
		.body("[1].fileName", equalTo("External-Json-File.txt"))
		.log().all();
		
	}
	
	@Test(priority = 2)
	public void downloadFile() {
		
		given()
		
		.when()
		.get("http://localhost:8080/downloadFile/students.json")
		
		.then()
		.statusCode(200)
		.log().body();
	}

}
