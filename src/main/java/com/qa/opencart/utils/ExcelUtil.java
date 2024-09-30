package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH = "";
/*	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		System.out.println("reading data from sheet: "+sheetName);
		Object[][] data = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = workbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i =0 ;i<sheet.getLastRowNum();i++) {
				for(int j =0 ;i<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				  }
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    }catch(InvalidFormatException e)
	    {
			e.printStackTrace();
		} 
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        return data;
	


	}
*/
}
