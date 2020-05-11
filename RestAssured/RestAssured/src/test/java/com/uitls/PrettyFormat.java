package com.uitls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PrettyFormat {

	public static String printPretty(String response)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}
	
	public static String getJson(Object obj)
	{
		 Gson gson = new Gson();
		 return gson.toJson(obj);
	}
}
