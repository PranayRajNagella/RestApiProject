package com.uitls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import io.restassured.*;

public class RequestResponseBuilders {
	
	
	PropertiesReadWrite p=new PropertiesReadWrite();
	public RequestSpecification requestBuilder() throws IOException
	{
		
		RequestSpecBuilder rb=new RequestSpecBuilder();
		RequestSpecification request=rb.setBaseUri(p.read("baseUri")).setContentType(ContentType.JSON).build();
		return given().spec(request);
	
	}
	
	public ResponseSpecification responseBuilder()
	{
		ResponseSpecBuilder RSB=new ResponseSpecBuilder();
		ResponseSpecification response=RSB.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return response;
	}
	
	public JsonPath jsonParser(String response)
	{
		JsonPath js=new JsonPath(response);
		return js;
	}

}
