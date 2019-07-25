import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//import org.apache.poi.ss.usermodel.DataFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ExcelToJson {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {
		// Can change to any path... just make sure to copy excel file into eclipse-workspace
		String filePath = "C:\\Users\\lkwong\\eclipse-workspace\\ExcelJson\\UAT_TEST_DATA.xlsx";
		
		// Put all the applicants into a list
		List applicants = readFile(filePath);
		// Prints the json object into string with values
		String output = convertObjectsToJsonString(applicants);
				
		System.out.println(output);
	}
	
	/**
	 * Iterates over excel file and grabs data needed
	 * 
	 * @param String filePath
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List readFile(String filePath) {
		try {
			// Creates excelfile to read
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			workbook.createSheet("applicant");
			XSSFSheet sheet = workbook.getSheet("DS1-200"); // Grabs specific sheet
    		
			List listApplicants = new ArrayList();
			
			HSSFDataFormatter hdf = new HSSFDataFormatter(); // Formats number-based data(zip, phone)

			
			// i is rows in excel
			for (int i = 1; i < 200; i++) {
			    Row row = sheet.getRow(i); // Grabs the first row of data
			    if(row == null) { 
			        continue;
			    }
				applicant applicant = new applicant(); // Create new applicant to put in data
			    
			    for (int j = 0; j < 21; j++) {
			        Cell cell = row.getCell(j); // Iterate over cols
			        if (j == 0) {
			        	applicant.setcountry("US"); // hard code country and email
			        	applicant.setemail("test@example.org");
			        }
			        
			        // Grabs (row,col) data values
			        if(cell == null) {
			            continue;
			        }
			        else if (j == 10) { // Firstname
			        	applicant.setfirstName(cell.getStringCellValue());
			        }
			        else if (j == 12) { // lastname
			        	applicant.setlastname(cell.getStringCellValue());
			        }
			        else if (j == 13) { // house # -> street address
			        	applicant.setstreetaddress(hdf.formatCellValue(cell) + " ");
			        }
			        else if (j == 14) { // streetname ->Street address
			        	applicant.setstreetaddress(cell.getStringCellValue() + " ");
			        }
			        else if (j == 15) { // street suffix -> street address
			        	applicant.setstreetaddress(cell.getStringCellValue());
			        }
			        else if (j == 18) { // city
			        	applicant.setcity(cell.getStringCellValue());
			        }
			        else if (j == 19) { // state
			        	applicant.setstate(cell.getStringCellValue());
			        }
			        else  if (j == 20) { // zip
			        	applicant.setzip(hdf.formatCellValue(cell));
			        }
			        else if (j == 8) { // phone
			        	applicant.setphone(hdf.formatCellValue(cell));
			        }
			    }
			    listApplicants.add(applicant); // adds to list of applicants
			}
			workbook.close();
			return listApplicants;
    		
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }	
		
	}
	
	/**
	 * Convert Java Objects to JSON String
	 * 
	 * @param customers
	 */
//	@SuppressWarnings("rawtypes")
	private static String convertObjectsToJsonString(List applicants) {
    	ObjectMapper mapper = new ObjectMapper(); // Converts applicants into a json string
    	String jsonString = "";
    	
    	try {
    		jsonString = mapper.writeValueAsString(applicants);
    	} catch (JsonProcessingException e) {
    		e.printStackTrace();
    	}
    	
    	return jsonString; 
	}


}
