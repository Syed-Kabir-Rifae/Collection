package com.ibm.Edition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.AdditionDao.PDFGenerator;
import com.ibm.AdditionDao.ExcelGen;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.io.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.AdditionDao.Excel1;
import com.ibm.AdditionDao.Sum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.InputStreamResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AdditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdditionApplication.class, args);
	}


	@RestController
	public class AddController {
		
	
		
		int resu;
		@RequestMapping(value ="/Add", method= RequestMethod.POST, consumes="application/json")
		public String getNumber(@RequestBody Sum s ) {
		return "Sum is "  +s.add();
		}
	  @GetMapping(value="/Profile", produces = MediaType.TEXT_PLAIN_VALUE)
	    public String index() {
		    
	        return "The result is " +resu;
	    }

	  @GetMapping(value="/ProfileAdd", produces = MediaType.TEXT_PLAIN_VALUE)
	    public String index2() {
		  int a=23,b=2,k;
		  k= sum(a,b);
		  return "sum is" +k;
		  
	  }
	
	@RequestMapping(value ="/Add2", method= RequestMethod.POST, consumes="application/json")
	public String getNumber2(@RequestBody Sum s2 ) {
	    int num1=s2.getA();
	    int num2=s2.getB();
	    String a=s2.getC();
	    resu=sum(num1,num2);
		
		
		return "Sum is "+a;
	}
	@CrossOrigin("http://localhost:4200")
	@RequestMapping(value="/export/excel12",method=RequestMethod.POST,consumes ="application/json")
	public ResponseEntity<InputStreamResource> ExportExcel1(@RequestBody Excel1 e1) throws IOException, ParseException{
		String s1= e1.getJsondata();
		HttpHeaders headers =new HttpHeaders();
		JSONParser parser = new JSONParser();
		//Display(s1);
		//JSONArray data1 = (JSONArray)parser.parse(new FileReader((s1)));

	ByteArrayInputStream b=ExcelGen.JsonToExcel1(s1);
		headers.add("Context.Disposition", "inline; filename=Excel1.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(b));
//return "Result is" +s1;
	}
	
	
	@CrossOrigin("http://localhost:4200")
	@RequestMapping(value="/export/pdf",method=RequestMethod.POST,consumes ="application/json")
	public ResponseEntity<InputStreamResource> ExportPdf(@RequestBody Excel1 p1) throws IOException, ParseException{
		String s2= p1.getJsondata();
		HttpHeaders headers =new HttpHeaders();
		JSONParser parser = new JSONParser();
		//Display(s1);
		//JSONArray data1 = (JSONArray)parser.parse(new FileReader((s1)));

	ByteArrayInputStream c=PDFGenerator.Generator1(s2);
		headers.add("Context.Disposition", "inline; filename=PdfEmployee.pdf");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(c));
//return "Result is" +s1;
	}
	
	
String Display(String s) {
	return s;
}
	
	
	//@CrossOrigin("http://localhost:4200")
	@RequestMapping(value="/export/excel" , method =RequestMethod.GET)
	public ResponseEntity<InputStreamResource> ExportExcel() throws IOException, ParseException {
		System.out.println("coming");
		 HttpHeaders headers =new HttpHeaders();
		 JSONParser parser = new JSONParser();
		 String file="C:\\Users\\003C6G744\\Desktop\\Json\\Sample.json";
		 JSONArray data = (JSONArray)parser.parse(new FileReader((file)));
		 ByteArrayInputStream b= JsonToExcel(data);
		 headers.add("Context.Disposition", "inline; filename=Excel.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(b));
			
			
		}
	
	//Comments staated from below
	private ByteArrayInputStream JsonToExcel(JSONArray dat) {

		ByteArrayOutputStream out= new ByteArrayOutputStream();
		try {
			
			//Taking Input JSON File
			 JSONArray a=dat;
			//Create workbook in .xlsx format
			Workbook workbook = new XSSFWorkbook();
		
		
			//Create Sheet
			Sheet sh = workbook.createSheet("Employee Data");
			
			//Create top row with column headings
			String[] colHeadings = {"Employee Name","Employee ID","Location"," Role"," Rating","Date of Joining","    Salary"};
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
			
			
			//Create the header row
			Row headerRow = sh.createRow(0);
			
			
			//Iterate over the column headings to create columns
			for(int i=0;i<colHeadings.length;i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(colHeadings[i]);
				cell.setCellStyle(headerStyle);
			}
			//Freeze Header Row
			sh.createFreezePane(0, 1);

	
			CreationHelper creationHelper= workbook.getCreationHelper();
			CellStyle dateStyle = workbook.createCellStyle();
			dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("₹ #,##0.00"));
		    String arr[] ={"name","id","location","role","rating","date"};
			
			int rownum =1;
			 for (Object i : a)
			  {
				 int k=0;
			  
				JSONObject p = (JSONObject) i;
			    Row row = sh.createRow(rownum++);
			    
			    while(k<6) {
				 Cell cell=row.createCell(k);
				CellStyle cellSt = workbook.createCellStyle();
				cellSt.setWrapText(true);
				cellSt.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellValue((String) p.get(arr[k]));
				cell.setCellStyle(cellSt);
				k++;
				 }
			    
			    
				Cell dateCell = row.createCell(6);
				dateCell.setCellValue((int) p.get("salary"));
				dateCell.setCellStyle(dateStyle);
				
			  }	

			//Autosize columns
			for(int i=0;i<colHeadings.length;i++) {
				sh.autoSizeColumn(i);
			}
			//Write the output to file
//			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\003C6G744\\Desktop\\Json\\employee.xlsx");
//			FileOutputStream fileOut = new FileOutputStream("Employee.xlsx");
//			workbook.write(fileOut);
//			fileOut.close();
//			workbook.close();
			workbook.write(out);
			workbook.close();
					
		}
		catch(Exception e) {
			e.printStackTrace();
	}
		System.out.println("Completed");
//		java.net.URL url = getClass().getResource("arml.xml");
//	     File file = new File(url.getPath());

		 
		return new ByteArrayInputStream(out.toByteArray());
		
		 }	// TODO Auto-generated method stub
	

public int sum(int a ,int b)
	{
		return a+b;
	}


		}}

//	@RestController
//	@RequestMapping("/employee23")
//	public class EmployeeController1 {
//		@Autowired
//	 private final com.ibm.AdditionDao.EmployeeService employeeService;
//	 public EmployeeController1(com.ibm.AdditionDao.EmployeeService employeeService) {
//		this.employeeService=employeeService; 
//	 }
//	 @GetMapping("/all")
//	 public ResponseEntity<List<com.ibm.AdditionDao.Employee>> getAllEmployees(){
//		 List<com.ibm.AdditionDao.Employee> employee =employeeService.findAllEmployee();
//		 return new ResponseEntity<>(employee,HttpStatus.OK);
//	 }
////	 @GetMapping("/find{id}")
////	 public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
////		 Employee employee=employeeService.FindEmployeeByid(id);
////		 return new ResponseEntity<>(employee,HttpStatus.OK);
////	 }
////	 @PostMapping("/addEmployee")
////	 public ResponseEntity<Employee>AddEmployee(@RequestBody Employee employee){
////		 Employee Newemployee= employeeService.addEmployee(employee);
////		 return new ResponseEntity<>(Newemployee,HttpStatus.CREATED);
////	 }
////	 @PutMapping("/Update")
////	 public ResponseEntity<Employee> UpdateEmployee(@RequestBody Employee employee){
////		 Employee updateEmployee =employeeService.updateEmployee(employee);
////		 return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
////	 }
//	 @DeleteMapping("/delete/{id}")
//	 public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
//		 employeeService.DeleteEmployee(id);
//		 return new ResponseEntity<>(HttpStatus.OK);
//	 }}}