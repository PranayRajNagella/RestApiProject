package com.uitls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReadWrite {
	
	
public static Properties p;
	
	public PropertiesReadWrite()
	{ 
		this.p=new Properties();
	}
	
	public  String read(String x) throws IOException{

			File f=new File(GlobalConstantsUtils.propertieslocation+"\\"+GlobalConstantsUtils.propfilename);
			FileInputStream fis=new FileInputStream(f);
			p.load(fis);
			String data = p.getProperty(x);
			fis.close();
			return data;
			
		}

	public static void write(String key,String value) throws IOException{
		File f=new File(GlobalConstantsUtils.propertieslocation+"\\"+GlobalConstantsUtils.propfilename);
		FileInputStream fis=new FileInputStream(f);
		Properties p1=new Properties();
		p1.load(fis);
		FileOutputStream fos=new FileOutputStream(f);

		
		p1.setProperty(key, value);
		p1.store(fos, "MALAY's WORLD");
		
		
	}



}
