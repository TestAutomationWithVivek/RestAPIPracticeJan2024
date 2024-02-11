package Day6_JSon_And_XML_Schema_Validation;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeserialization {
	
	//Serialization means converting POJO class to json object
	//Deserialixation means converting json object to POJO
	//It happens interanlly in API when we send request, POJO's encapculation and security and enhancement is done at run time
	
	
	//@Test()
	public void convertPOJO2Json() throws JsonProcessingException {
		
		Students students = new Students();
		
		students.setName("vivek");
		students.setLocation("pune");
		students.setPhone("0000000");
		String [] coursesArray = {"c", "c++"};
		students.setCourses(coursesArray);
		
		//convert java object (POJO) to ----> JSon Object (Serialization)
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
		
		System.out.println(jsonData);
	}
	
	@Test()
	public void convertJson2POJO() throws JsonMappingException, JsonProcessingException {
		String jsonData = "{\r\n"
				+ "  \"name\" : \"vivek\",\r\n"
				+ "  \"location\" : \"pune\",\r\n"
				+ "  \"phone\" : \"0000000\",\r\n"
				+ "  \"courses\" : [ \"c\", \"c++\" ]\r\n"
				+ "}";
		
		//converting json data ----->>>> Pojo object
		ObjectMapper objectMapper = new ObjectMapper();
		Students stu =  objectMapper.readValue(jsonData, Students.class);
		System.out.println("Name = " + stu.getName());
		System.out.println("phone = " + stu.getPhone());
		System.out.println("location = "+stu.getLocation());
		System.out.println("course 1 = " +stu.getCourses()[0]);
		System.out.println("course 2 = " + stu.getCourses()[1]);
	}

}
