package com.uitls;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	 public static HashMap<String,String> storeValues = new HashMap();
	   public static List<HashMap<String,String>> data(String sheetName,String testcasename)
	   {
	      List<HashMap<String,String>> mydata = new ArrayList<>();
	      try
	      {
	         FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//Resources//Booking.xlsx");
	         XSSFWorkbook workbook = new XSSFWorkbook(fs);
	         XSSFSheet sheet = workbook.getSheet(sheetName);
	         
	         int firstcount=0;
	         for(int i=0;i<=sheet.getLastRowNum();i++)
	         {
	        	 if(testcasename.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue()))
	        	 {
	        		 firstcount=i;
	        		 break;
	        	 }
	         }
	         
	         int latscount=firstcount;
	         for(int i=firstcount+1;i<=sheet.getLastRowNum();i++)
	         {
	        	 if(testcasename.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue()))
	        	 {
	        		 latscount++;
	        	 }
	         }
	         
	         System.out.println(firstcount+" "+latscount);
	         
	         Row HeaderRow = sheet.getRow(0);
	      //   for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
	         for(int i=firstcount;i<=latscount;i++)
	         {
	            Row currentRow = sheet.getRow(i);
	            HashMap<String,String> currentHash = new HashMap<String,String>();
	            for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
	            {
	               Cell currentCell = currentRow.getCell(j);
	               switch (currentCell.getCellType())
	               {
	               case Cell.CELL_TYPE_STRING:
	                 // System.out.print(currentCell.getStringCellValue() + "\t");
	                  currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
	                  break;
	               }
	            }
	            mydata.add(currentHash);
	         }
	         fs.close();
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return mydata;
	   }
	}


