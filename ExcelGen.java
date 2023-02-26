package com.ibm.AdditionDao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class ExcelGen {
public static ByteArrayInputStream JsonToExcel1(String dat) {

	ByteArrayOutputStream out= new ByteArrayOutputStream();
	try {
		
		//Taking Input JSON File
		// JSONArray a=dat;
		//Create workbook in .xlsx format
		 
		 	
			Object object=null;
			JSONArray a=null;
		JSONParser jsonParser=new JSONParser();
			object=jsonParser.parse(dat);	
			a=(JSONArray) object;
		Workbook workbook = new XSSFWorkbook();

			//Create Sheet
			Sheet sh = workbook.createSheet("Employee Data");
			
	//Create top row with column headings
			//String[] colHeadings = {"Employee Name","Employee ID","Location"," Role"," Rating","Date of Joining","    Salary"};
			String arr[] ={"Name","Id","Location","Role","Rating","Date","Salary"};
		//We want to make it bold with a foreground color.
			Font headerFont = workbook.createFont();
		headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short)12);
			headerFont.setColor(IndexedColors.BLACK.index);
			
			
			//Create a CellStyle with the font
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(IndexedColors.YELLOW1.index);
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			
			//Create the header row
		Row headerRow = sh.createRow(0);
			
			
		//Iterate over the column headings to create columns
			for(int i=0;i<arr.length;i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(arr[i]);
				cell.setCellStyle(headerStyle);
				
		}
			//Freeze Header Row
	sh.createFreezePane(0, 1);
	
		CreationHelper creationHelper= workbook.getCreationHelper();
			CellStyle dateStyle = workbook.createCellStyle();
			dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("₹ #,##0.00"));
		    
			int k=0;
			int rownum =1;
		    
          
			int size=arr.length;
          for (Object i : a)
		  {
				 
			  k=0;
				JSONObject p = (JSONObject) i;
			    Row row = sh.createRow(rownum++);
			  
			    while(k<size) {
			  if(arr[k]!="Salary")  {
				 Cell cell=row.createCell(k);
				CellStyle cellSt = workbook.createCellStyle();
				cellSt.setWrapText(true);
				cellSt.setAlignment(HorizontalAlignment.CENTER);
			    cell.setCellValue((String) p.get(arr[k]));
//				cell.setCellValue((String) p.getAsString(arr[k]));
				cell.setCellStyle(cellSt);
			k++;
			 }
		 else {
				Cell dateCell = row.createCell(k);
				dateCell.setCellValue((int) p.get(arr[k]));
				dateCell.setCellStyle(dateStyle);
				k++;
			  }	}
		  }
			//Autosize columns
			for(int i=0;i<arr.length;i++) {
				sh.autoSizeColumn(i);
		}
		
		//Write the output to file
//		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\003C6G744\\Desktop\\Json\\employee.xlsx");
//		FileOutputStream fileOut = new FileOutputStream("Employee.xlsx");
//		workbook.write(fileOut);
//		fileOut.close();
//		workbook.close();
		workbook.write(out);
		workbook.close();
				
	}
	catch(Exception e) {
		e.printStackTrace();
}
	System.out.println("Completed");
//	java.net.URL url = getClass().getResource("arml.xml");
//     File file = new File(url.getPath());

	 
	return new ByteArrayInputStream(out.toByteArray());
	
	 }	// TODO Auto-generated method stub
}
