package RestAssured_201.RestAssured;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.uitls.ExtentFile;
import com.uitls.RequestResponseBuilders;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="FeatureFiles",glue={"stepdefnitions"})
public class TestRunner {
	static RequestResponseBuilders re;
	protected static String token;
	@BeforeClass()
	public static void setup() throws IOException
	{
		ExtentFile.setExtent();
		token=AccessToken.getToken();
		System.out.println("*******START TEST*******");
	}
	@AfterClass()
	public static void end() throws Exception
	{
		ExtentFile.endReport();
		System.out.println("*******END TEST*******");
	}
}


