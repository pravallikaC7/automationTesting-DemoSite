package loginSection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Credentials {
	
	//Declare a 2 dimensions array to store all the test data read from excel
	static String[][] loginCredentials = null;

	public static String[][] readExcel(String filePath, String fileName, String sheetName) throws IOException {

		// Create an object of File class to open xlsx file
		File file = new File(filePath);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook credentials = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			credentials = new XSSFWorkbook(inputStream);
		}

		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			credentials = new HSSFWorkbook(inputStream);
		}

		// Read sheet inside the workbook by its name
		Sheet login = credentials.getSheet(sheetName);
		
		// Find number of rows and columns in excel file
		int rowCount = login.getPhysicalNumberOfRows();
		int columnCount = login.getRow(0).getPhysicalNumberOfCells();

		Credentials.loginCredentials = new String[rowCount][columnCount+1];
		
		// Create a loop over all the rows of excel file to read it		
		for (int i = 0; i < rowCount; i++) {
			Row row = login.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < columnCount; j++) {
				loginCredentials[i][j] = row.getCell(j).getStringCellValue();
			}
			
		}
		return (loginCredentials);
		
	}
}
