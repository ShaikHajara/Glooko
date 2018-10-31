package com.glooko.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static XSSFWorkbook xssfworkbook;
	public static XSSFSheet xssfsheet;
	public static XSSFRow xssfrow;
	public static FileInputStream fis;
	public static File file;
	public static int totalRows;
	public static int totalColumns;
	public static String DataFromExcel;
	public static List<String> list = new ArrayList<String>();

	public static void getDataFromExcel(String Filename, String Sheetname) throws InvalidFormatException, IOException {
		file = new File(Filename);
		fis = new FileInputStream(file);
		xssfworkbook = new XSSFWorkbook(fis);
		xssfsheet = xssfworkbook.getSheet(Sheetname);
		totalRows = xssfsheet.getPhysicalNumberOfRows();
		xssfrow = xssfsheet.getRow(0);
		totalColumns = xssfrow.getPhysicalNumberOfCells();
		for (int i = 1; i <= totalRows - 1; i++) {
			for (int j = 0; j < totalColumns; j++) {
				xssfrow = xssfsheet.getRow(i);
				DataFromExcel = xssfrow.getCell(j).getStringCellValue();
				list.add(DataFromExcel);
			}
		}
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}
}
