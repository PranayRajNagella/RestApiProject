package stepdefnitions;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.uitls.ExcelUtils;
import com.uitls.ExtentFile;
import com.uitls.PrettyFormat;
import com.uitls.PropertiesReadWrite;
import com.uitls.RequestResponseBuilders;
import com.uitls.TestDataBuilder;
import com.uitls.TextFileUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateBookingSteps {
	public String response;
	public static Response resp;
	public static ExtentTest test;
	TestDataBuilder builder;
	ArrayList<String> request;
	static RequestResponseBuilders re;
	static int bookingid;
	static Map<String, Object> headerMap;
	PropertiesReadWrite p = new PropertiesReadWrite();
	String  Bookingid,request1,createresponse;

	@Given("when user hit the given uri")
	public void when_user_hit_the_given_uri() throws IOException {
		test = ExtentFile.extent.createTest("CreateBooking API validation");
		test.log(Status.INFO, "Endpoint Invoked: POST " + p.read("baseUri") + p.read("CreateBookingResource"));
	}

	@When("user calls the booking api with random valid input")
	public void user_calls_the_booking_api_with_random_valid_input() throws ParseException, IOException {
		builder = new TestDataBuilder();
		test.log(Status.INFO, "Posting the following request to the api");
		request = new ArrayList<String>();
		/*
		 * generate Data
		 */
		Faker fake = new Faker();
		request.add(fake.name().firstName().toString());
		request.add(fake.name().lastName().toString());
		request.add(Integer.toString(new Random().nextInt(999)));
		request.add("True");
		// List<String> dates=new ArrayList<String>();
		request.add("2018-01-01");
		request.add("2019-01-01");
		request.add(
				"Get this dish " + fake.food().dish() + " " + Integer.toString(new Random().nextInt(9)) + " Plates");

		String s = PrettyFormat.getJson(builder.createBookingPayLoad(request)).toString();
		test.log(Status.INFO, "<pre>" + "Request Formatted JSON: "
				+ PrettyFormat.printPretty(s).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");

		headerMap = new HashMap<String, Object>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Accept", "application/json");
		re = new RequestResponseBuilders();
		resp = re.requestBuilder().headers(headerMap).body(builder.createBookingPayLoad(request)).when()
				.post(p.read("CreateBookingResource"));
	}

	@Then("Api call is success with status following status code {int}")
	public void api_call_is_success_with_status_following_status_code(Integer int1) {
		response = resp.then().extract().asString();
		createresponse=response;
		System.out.println(response);
		test.log(Status.INFO, "Status code: " + Integer.toString(resp.getStatusCode()));
		test.log(Status.PASS, "<pre>" + "Response Formatted JSON: "
				+ PrettyFormat.printPretty(response).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
		boolean result = int1 == resp.getStatusCode();
		try {
			Assert.assertTrue(result);
			test.log(Status.PASS,"Actual Status code: " + Integer.toString(resp.getStatusCode())+"Expected Status Code: "+int1);
		} catch (AssertionError e) {
			test.log(Status.FAIL,"Actual Status code: " + Integer.toString(resp.getStatusCode())+"Expected Status Code: "+int1);
		}
	}

	@Then("Response contains the expected text as {string}")
	public void response_contains_the_expected_text_as(String string) throws Exception {
		System.out.println(resp.getStatusLine());
		System.out.println(resp.getStatusLine().contains(string));
		JsonPath js = new JsonPath(response);
		if(response.contains("bookingid"))
		{	
			bookingid = js.getInt("bookingid");
			System.out.println(bookingid + "Printing the booking id");
			try {
				Assert.assertTrue(resp.getStatusLine().contains(string));
				test.log(Status.PASS,"Actual Status line: " +resp.getStatusLine()+" Contains Expected text "+string);
			} catch (AssertionError e) {
				test.log(Status.FAIL,"Actual Status line: " +resp.getStatusLine()+" Dose not Contains Expected text "+string);
			}
		}
		TextFileUtils.appendStrToFile("CreateBookingResponse.txt", response);
	}

	@Then("verify the ResponseStructure is as Expected")
	public void verify_the_ResponseStructure_is_as_Expected() throws IOException {
		
		if(resp.getStatusCode()==200)
		{
		headerMap = new HashMap<String, Object>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Accept", "application/json");
		resp = re.requestBuilder().headers(headerMap).pathParam("id", bookingid).when()
				.get(p.read("GetBookingResources"));
		response = resp.then().extract().asString();
		System.out.println(response);
		test.log(Status.PASS, "Status code: " + Integer.toString(resp.getStatusCode()));
		test.log(Status.PASS, "<pre>" + "GET Booking Response Formatted JSON: "
				+ PrettyFormat.printPretty(response).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
		JsonPath createbooking=re.jsonParser(createresponse);
		JsonPath getbooking=re.jsonParser(response);
		try{
		Assert.assertEquals(createbooking.getString("booking.firstname"),getbooking.getString("firstname"));
		Assert.assertEquals(createbooking.getString("booking.lastname"),getbooking.getString("lastname"));
		Assert.assertEquals(createbooking.getInt("booking.totalprice"),getbooking.getInt("totalprice"));
		Assert.assertEquals(createbooking.getBoolean("booking.depositpaid"),getbooking.getBoolean("depositpaid"));
		Assert.assertEquals(createbooking.getString("booking.bookingdates.checkin"),getbooking.getString("bookingdates.checkin"));
		Assert.assertEquals(createbooking.getString("booking.bookingdates.checkout"),getbooking.getString("bookingdates.checkout"));
		Assert.assertEquals(createbooking.getString("booking.additionalneeds"),getbooking.getString("additionalneeds"));
		test.log(Status.PASS,"Create booking Response and get booking details are matching");
		}
		catch (AssertionError error) {
			test.log(Status.FAIL, createbooking.getString("booking.firstname")+"\t"+getbooking.getString("firstname")
								 +"\n \t"+createbooking.getString("booking.lastname")+"\t"+getbooking.getString("lastname")
								 +"\n \t"+createbooking.getString("booking.totalprice")+"\t"+getbooking.getString("totalprice")
								 +"\n \t"+createbooking.getString("booking.depositpaid")+"\t"+getbooking.getString("depositpaid")
								 +"\n \t"+createbooking.getString("booking.bookingdates.checkin")+"\t"+getbooking.getString("bookingdates.checkin")
								 +"\n \t"+createbooking.getString("booking.bookingdates.checkout")+"\t"+getbooking.getString("bookingdates.checkout")
								 +"\n \t"+createbooking.getString("booking.additionalneeds")+"\t"+getbooking.getString("additionalneeds"));
			}
		}
	}

	@When("user calls the create booking api with the input from {string} and {string}")
	public void user_calls_the_create_booking_api_with_the_input_from_and(String Sheetname, String TestCaseName) throws NumberFormatException, IOException { 
		test.log(Status.INFO, "Posting the following request to the api");
		System.out.println(Sheetname+" "+TestCaseName);
		List<HashMap<String,String>> mapdata=ExcelUtils.data(Sheetname,TestCaseName);
		ArrayList<String> data=new ArrayList<>();
		for(int i=0;i<mapdata.size();i++)
		{
					data.add(mapdata.get(i).get("TestData"));
					request1=data.get(i);
			test.log(Status.INFO, "<pre>" + "Request Formatted JSON: "
					+ PrettyFormat.printPretty(request1).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
			headerMap = new HashMap<String, Object>();
			headerMap.put("Content-Type", "application/json");
			headerMap.put("Accept", "application/json");
			re = new RequestResponseBuilders();
			resp=re.requestBuilder().headers(headerMap)
					.body(request1).when()
					.post(p.read("CreateBookingResource"));
		}
	}
}
