package com.uitls;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.github.opendevl.JFlat;

public class TextFileUtils {
	public static void appendStrToFile(String fileName, String str) {
		try {

			// Open given file in append mode.
			BufferedWriter  out = new BufferedWriter(new FileWriter(GlobalConstantsUtils.propertieslocation+"//"+fileName, true));
			out.write(str+"\n");
			out.close();
		} catch (IOException e) {
			System.out.println("exception occoured" + e);
		}
	}
	
	public static void jsontoCSV(String filename,String str) throws Exception
	{
		JFlat flatMe = new JFlat(str);
		
		//get the 2D representation of JSON document
        flatMe.json2Sheet().headerSeparator("_").getJsonAsSheet();

        //write the 2D representation in csv format
        flatMe.write2csv(GlobalConstantsUtils.propertieslocation+"//"+filename+".csv");
       

		//directly write the JSON document to CSV
		//flatMe.json2Sheet().write2csv(GlobalConstantsUtils.propertieslocation+"//"+filename+".csv");

		//directly write the JSON document to CSV but with delimiter
		//flatMe.json2Sheet().write2csv("/path/to/destination/file.json", '|');
	}
}
