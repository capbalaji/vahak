package genericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	static String dataPath;
	
	public Object readData(String dataPath,int row,int col) throws IOException {
		File excelFile = new File(dataPath);
		FileInputStream file = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Object value="";
		
		//value=sheet.getLastRowNum();
		
		if(sheet.getRow(row).getCell(col).getCellType().equals(CellType.NUMERIC))
		{
		value=sheet.getRow(row).getCell(col).getNumericCellValue();
		System.out.println("reading as numeric value:"+value.toString());
		}else if(sheet.getRow(row).getCell(col).getCellType().equals(CellType.STRING)){
			value=sheet.getRow(row).getCell(col).getStringCellValue();	
			System.out.println("reading as string value:"+value.toString());
		}
			
		workbook.close();
		file.close();		
		return value;
		
	}
	
	
	
	public void writeData(String dataPath,Object writethis,int row,int col,CellType type) throws IOException {
		File excelFile = new File(dataPath);
		FileInputStream file = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		//CellType.STRING
		if(sheet.getRow(row) == null) {
			sheet.createRow(row);
				}
		
		sheet.getRow(row).createCell(col).setCellType(type);
		//sheet.getRow(1).createCell(4).setCellType(CellType.NUMERIC);
		
		sheet.getRow(row).getCell(col).setCellValue(writethis.toString());
		
		
		FileOutputStream outFile = new FileOutputStream(excelFile);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		file.close();
	}
	
	
	/*
	public static void main(String[] abc) throws Throwable {
		ExcelUtils excl=new ExcelUtils();
		dataPath=System.getProperty("user.dir")+"/src/main/resources/Mytrip.xlsx";
		System.out.println("data path is:"+dataPath);
		System.out.println("value from method is :"+excl.readData(dataPath,1,2).toString());
		excl.writeData(dataPath, excl.readData(dataPath,1,0), 1, 0, CellType.STRING);
		excl.writeData(dataPath, excl.readData(dataPath,1,1), 1, 1, CellType.STRING);
		excl.writeData(dataPath, excl.readData(dataPath,1,2), 1, 2, CellType.STRING);
		//TestName	flightname	depttime	duration	arrvtime	actualfare
		
	}*/
}
