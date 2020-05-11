package com.uitls;

import java.util.ArrayList;
import com.model.AccessTokenRequestPOJO;
import com.model.CreateBookingRequestPOJO;
import com.model.UpdateBookingRequestPOJO;


public class TestDataBuilder {

	
	public CreateBookingRequestPOJO createBookingPayLoad(ArrayList<String> request)
	{
		CreateBookingRequestPOJO req=new CreateBookingRequestPOJO();
		req.setFirstname(request.get(0));
		req.setLastname(request.get(1));
		req.setTotalprice(Integer.parseInt(request.get(2)));
		req.setDepositpaid(Boolean.parseBoolean(request.get(3)));
		CreateBookingRequestPOJO.BookingDates dates=new CreateBookingRequestPOJO.BookingDates();
		dates.setCheckin(request.get(4));
		dates.setCheckout(request.get(5));
		req.setBookingdates(dates);
		req.setAdditionalneeds(request.get(6));
		return req;
	}

	public AccessTokenRequestPOJO acessToken(String username,String Password)
	{
		AccessTokenRequestPOJO req=new AccessTokenRequestPOJO();
		req.setUsername(username);
		req.setPassword(Password);
		return req;
	}
	
	
	public UpdateBookingRequestPOJO updateBookingRequest(ArrayList<String> request)
	{
		UpdateBookingRequestPOJO req=new UpdateBookingRequestPOJO();
		req.setFirstname(request.get(0));
		req.setLastname(request.get(1));
		req.setTotalprice(Integer.parseInt(request.get(2)));
		req.setDepositpaid(Boolean.parseBoolean(request.get(3)));
		UpdateBookingRequestPOJO.BookingDates dates=new UpdateBookingRequestPOJO.BookingDates();
		dates.setCheckin(request.get(4));
		dates.setCheckout(request.get(5));
		req.setBookingdates(dates);
		req.setAdditionalneeds(request.get(6));
		return req;
	}
	
	
	
	
	

}
