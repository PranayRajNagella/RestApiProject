package com.uitls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	//Hello adding some randomcommmentHello
	public static Date DateFormatter(String date,String format) throws ParseException
	{ 
			System.out.println(format+"create a clonfict");
			System.out.println("MY Code+ jave e  jsbdkjabsdjkbdbdfkjbdkjfb");
		    Date date1=new SimpleDateFormat(format).parse(date);
		    return date1;
	}

}
