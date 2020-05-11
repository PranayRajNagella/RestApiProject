package com.uitls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static Date DateFormatter(String date,String format) throws ParseException
	{ 
			System.out.println(format);
		    Date date1=new SimpleDateFormat(format).parse(date);
		    return date1;
	}

}
