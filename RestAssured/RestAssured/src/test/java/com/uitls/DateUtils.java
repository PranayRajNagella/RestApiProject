package com.uitls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	//Hello adding some randomcommment
	public static Date DateFormatter(String date,String format) throws ParseException
	{ 
			System.out.println(format);
		System.out.println("MY Code")
		    Date date1=new SimpleDateFormat(format).parse(date);
		    return date1;
	}

}
