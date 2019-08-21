import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelToJson {

	public static void main(String[] args) throws IOException {
		// Can change to any path... just make sure to copy excel file into eclipse-workspace
		String filePath = "C:\\Users\\lkwong\\eclipse-workspace\\ExcelJson\\UAT_TEST_DATA.xlsx";
		// Put all the applicants into a list
		List<Applicant> applicants = readFile(filePath);
		// Prints the json object into string with values
		String output = convertObjectsToJsonString(applicants);				
		System.out.println(output);
	}
	
	/**
	 * Iterates over excel file and grabs data needed
	 * 
	 * @param String filePath
	 */
	private static List<Applicant> readFile(String filePath) {
		try {
			// Creates excelfile to read
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			workbook.createSheet("applicant");
			XSSFSheet sheet = workbook.getSheet("DS1-200"); // Grabs specific sheet
			List<Applicant> listApplicants = new ArrayList<>();	
			HSSFDataFormatter hdf = new HSSFDataFormatter(); // Formats number-based data(zip, phone)	
			// i is rows in excel
			for (int i = 1; i < 200; i++) {
			    Row row = sheet.getRow(i); // Grabs the first row of data	   
			    if(row == null) continue;   
				Applicant applicant = new Applicant(); // Create new applicant to put in data	
			    for (int j = 0; j < 21; j++) {
			        Cell cell = row.getCell(j); // Iterate over cols			       
			        if (j == 0) {
			        	applicant.setCountry("US"); // hard code country and email
			        	applicant.setEmail("test@example.org");
			        }
			        if(cell == null)  continue;    
			        switch(j) {
			        	case 8:	 applicant.setPhone(hdf.formatCellValue(cell)); 		break;
			        	case 10: applicant.setFirstName(cell.getStringCellValue());		break;
			        	case 12: applicant.setLastname(cell.getStringCellValue()); 		break;
			        	case 13: applicant.setStreetAddress(hdf.formatCellValue(cell) + " "); 	break;
			        	case 14: applicant.setStreetAddress(cell.getStringCellValue() + " "); 	break;
			        	case 15: applicant.setStreetAddress(cell.getStringCellValue()); 	break;
			        	case 18: applicant.setCity(cell.getStringCellValue()); 			break;
			        	case 19: applicant.setState(cell.getStringCellValue()); 		break;
			        	case 20: applicant.setZip(hdf.formatCellValue(cell)); 			break;
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
	private static String convertObjectsToJsonString(List<Applicant> applicants) {
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
