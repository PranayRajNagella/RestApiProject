package RestAssured_201.RestAssured;

import java.io.IOException;

import com.uitls.PropertiesReadWrite;
import com.uitls.RequestResponseBuilders;
import com.uitls.TestDataBuilder;

import io.restassured.path.json.JsonPath;

public class AccessToken {
	static String token;
	
	public static String getToken() throws IOException
	{
		
		RequestResponseBuilders req=new RequestResponseBuilders();
		TestDataBuilder TB=new TestDataBuilder();
		PropertiesReadWrite p=new PropertiesReadWrite();
		token=req.requestBuilder().header("Content-Type","application/json")
		.body(TB.acessToken(p.read("tokenusername"),p.read("tokenpassword")))
		.when().post("/auth").then().extract().response().asString();
		JsonPath js=new JsonPath(token);
		token=js.get("token");
		return token;
		//return token;
	}

}
