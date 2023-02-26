package com.ibm.AdditionDao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Service;

//import org.apache.poi.ss.usermodel.Cell;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.element.Cell;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
@Service
public class PDFGenerator {

public static ByteArrayInputStream Generator1(String data) {
		
	ByteArrayOutputStream out= new ByteArrayOutputStream();
	try {

		Object object=null;
		JSONArray a=null;
	    JSONParser jsonParser=new JSONParser();
		object=jsonParser.parse(data);	
		a=(JSONArray) object;
		
	        
	      PdfWriter writer = new PdfWriter(out);             
	   
	      // Creating a PdfDocument object      
	      PdfDocument pdf = new PdfDocument(writer);                   
	      Document doc =new Document(pdf);
	      // Creating a Document object       
	     
	      String arr[] ={"Name","Id","Location","Role","Rating","Date","Salary"};
	      int k=0;
		  int size=arr.length;

		  
		//font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
	
		  PdfFont headerFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	      PdfFont cellFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	      Table table = new Table(size);
	      table.setWidth(UnitValue.createPercentValue(100));
	      // adding header
	      table.addHeaderCell(new Cell(1, size)
	           .setTextAlignment(TextAlignment.CENTER)
	           .setBackgroundColor(Color.YELLOW)
	           .add(new Paragraph("Employee Information")
	           .setFont(headerFont)));
	      

			    while(k<size) {
			  
	            table.addHeaderCell(new Cell()
	           .add(new Paragraph(arr[k])
	           .setFont(headerFont))
	           .setTextAlignment(TextAlignment.CENTER));
			   k++; }
		//doc.add(Paragraph);
		//doc.add(Chunk.NEWLINE);
		//para.setTextAlignment(TextAlignment.CENTER);
		for (Object i : a)
			  {
					 
				  k=0;
					JSONObject p = (JSONObject) i;
			
		
		while(k<size) {
			if(arr[k]!="Salary") {
String data1=(arr[k]);
		table.addCell(new Cell()
	             .add(new Paragraph(p.getAsString(data1))
	              
	              .setTextAlignment(TextAlignment.CENTER)));
		
		k++;}
			else{
				String data1=(arr[k]);
				table.addCell(new Cell()
			             .add(new Paragraph(p.getAsString(data1))
			              .setBackgroundColor(Color.GREEN)
			              .setTextAlignment(TextAlignment.RIGHT)));
				
			k++;}}
			}
		doc.add(table);
        doc.close();
}
          catch(Exception e) {
		System.out.println("Erre'");
	}
	return new ByteArrayInputStream(out.toByteArray());
}
}