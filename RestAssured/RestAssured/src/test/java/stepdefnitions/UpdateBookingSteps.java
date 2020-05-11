package stepdefnitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uitls.ExcelUtils;
import com.uitls.ExtentFile;
import com.uitls.PrettyFormat;
import com.uitls.PropertiesReadWrite;
import com.uitls.RequestResponseBuilders;
import com.uitls.TestDataBuilder;

import RestAssured_201.RestAssured.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateBookingSteps extends TestRunner {
	public String response;
	public static Response resp;
	public static ExtentTest test;
	TestDataBuilder builder;
	static RequestResponseBuilders re;
	static Map<String, Object> headerMap;
	PropertiesReadWrite p = new PropertiesReadWrite();
	String Bookingid, request, Partialbookingresponse;

	@Given("when user hit the given uri for Update Booking")
	public void when_user_hit_the_given_uri_for_Update_Booking() throws IOException {
		test = ExtentFile.extent.createTest("Update Booking API validation");
		test.log(Status.INFO, "Endpoint Invoked: PUT " + p.read("baseUri") + p.read("UpdateBookingResource"));
	}

	@When("user calls the update booking api with valid input from {string} and {string}")
	public void user_calls_the_update_booking_api_with_valid_input_from_and(String Sheetname, String TestCaseName)
			throws NumberFormatException, IOException {
		builder = new TestDataBuilder();
		test.log(Status.INFO, "Posting the following request to the api");
		System.out.println(Sheetname + " " + TestCaseName);
		List<HashMap<String, String>> mapdata = ExcelUtils.data(Sheetname, TestCaseName);
		ArrayList<String> data = new ArrayList<>();
		for (int i = 0; i < mapdata.size(); i++) {
			data.add(mapdata.get(i).get("TestData"));
			Bookingid = data.get(i).split("##")[0];
			request = data.get(i).split("##")[1];
			test.log(Status.INFO, "with path parameter booking id :" + Bookingid);
			test.log(Status.INFO, "<pre>" + "Request Formatted JSON: "
					+ PrettyFormat.printPretty(request).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
			headerMap = new HashMap<String, Object>();
			headerMap.put("Content-Type", "application/json");
			headerMap.put("Accept", "application/json");
			headerMap.put("Cookie", "token=" + token);
			re = new RequestResponseBuilders();

			resp = re.requestBuilder().headers(headerMap).pathParam("id", Integer.parseInt(Bookingid)).body(request)
					.when().put("/booking/{id}");
		}
	}

	@Then("Api call is success with the following Status Code {int}")
	public void api_call_is_success_with_the_following_Status_Code(Integer int1) {

		response = resp.then().extract().asString();
		Partialbookingresponse = response;
		System.out.println(response);
		test.log(Status.PASS, "Status code: " + Integer.toString(resp.getStatusCode()));
		test.log(Status.PASS, "<pre>" + "Response Formatted JSON: "
				+ PrettyFormat.printPretty(response).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
		boolean result = int1 == resp.getStatusCode();
		try {
			Assert.assertTrue(result);
			test.log(Status.PASS,
					"Actual Status code: " + Integer.toString(resp.getStatusCode()) + "Expected Status Code: " + int1);
		} catch (AssertionError e) {
			test.log(Status.FAIL,
					"Actual Status code: " + Integer.toString(resp.getStatusCode()) + "Expected Status Code: " + int1);
		}
	}

	@Then("verify the update booking response against the get booking")
	public void verify_the_update_booking_response_against_the_get_booking() throws IOException {
		if (resp.getStatusCode() == 200) {
			headerMap = new HashMap<String, Object>();
			headerMap.put("Content-Type", "application/json");
			headerMap.put("Accept", "application/json");
			resp = re.requestBuilder().headers(headerMap).pathParam("id", Bookingid).when()
					.get(p.read("GetBookingResources"));
			response = resp.then().extract().asString();
			System.out.println(response);
			test.log(Status.PASS, "Status code: " + Integer.toString(resp.getStatusCode()));
			test.log(Status.PASS, "<pre>" + "GET Booking Response Formatted JSON: "
					+ PrettyFormat.printPretty(response).replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
			JsonPath Updateresponse = re.jsonParser(Partialbookingresponse);
			JsonPath getbooking = re.jsonParser(response);
			try {
				Assert.assertEquals(Updateresponse.getString("firstname"), getbooking.getString("firstname"));
				Assert.assertEquals(Updateresponse.getString("lastname"), getbooking.getString("lastname"));
				Assert.assertEquals(Updateresponse.getInt("totalprice"), getbooking.getInt("totalprice"));
				Assert.assertEquals(Updateresponse.getBoolean("depositpaid"), getbooking.getBoolean("depositpaid"));
				Assert.assertEquals(Updateresponse.getString("bookingdates.checkin"),
						getbooking.getString("bookingdates.checkin"));
				Assert.assertEquals(Updateresponse.getString("bookingdates.checkout"),
						getbooking.getString("bookingdates.checkout"));
				Assert.assertEquals(Updateresponse.getString("additionalneeds"),
						getbooking.getString("additionalneeds"));
				test.log(Status.PASS,"Updated booking Response and get booking details are matching");
			} catch (AssertionError error) {
				test.log(Status.FAIL, Updateresponse.getString("firstname") + "\t" + getbooking.getString("firstname")
						+ "\n \t" + Updateresponse.getString("lastname") + "\t" + getbooking.getString("lastname")
						+ "\n \t" + Updateresponse.getString("totalprice") + "\t" + getbooking.getString("totalprice")
						+ "\n \t" + Updateresponse.getString("depositpaid") + "\t" + getbooking.getString("depositpaid")
						+ "\n \t" + Updateresponse.getString("bookingdates.checkin") + "\t"
						+ getbooking.getString("bookingdates.checkin") + "\n \t"
						+ Updateresponse.getString("bookingdates.checkout") + "\t"
						+ getbooking.getString("bookingdates.checkout") + "\n \t"
						+ Updateresponse.getString("additionalneeds") + "\t" + getbooking.getString("additionalneeds"));
			}
		}
	}

}
