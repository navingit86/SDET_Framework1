package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	
	
	public ExcelUtility(String path)
	{
		this.path = path;
		
	}
	
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
		
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
	//	String celldata = cell.toString();
		
		String celldata;
		DataFormatter df = new DataFormatter();
		try {
		celldata = df.formatCellValue(cell);	// returns formatted value of the cell into String
		}
		catch (Exception e)
		{
			celldata="";
		}
		
		workbook.close();
		fi.close();
		return celldata;	
	}
	
	public void setCellData (String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File xlfile = new File(path);
		if(!xlfile.exists())			// if file doens't exist then create one
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1)	// If sheet not exist then create new sheet
			workbook.createSheet(sheetName);		// As we don;t have braces only this statement is executed if true
		sheet = workbook.getSheet(sheetName);		// this won't be considered as a part of if statement

		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo= new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	
	

}
