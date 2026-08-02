package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.LoginApiRequest;

public class ExcelFileReaderPractise {
	public static Iterator<LoginApiRequest> loadExcel(String excelFilePath) {
		InputStream inputStream;
		XSSFWorkbook xssfWorkbook;
		XSSFSheet sheet;
		int userNameIndex = -1;
		int passwordIndex = -1;
		XSSFRow headerRow;
		ArrayList<LoginApiRequest> userList = new ArrayList<LoginApiRequest>();
		int lastRowIndex;
		XSSFRow rowData;
		LoginApiRequest loginApiRequest;

		try {
			inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(excelFilePath);
			xssfWorkbook = new XSSFWorkbook(inputStream);
			sheet = xssfWorkbook.getSheet("LoginTestData");
			headerRow = sheet.getRow(0);
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
					userNameIndex = cell.getColumnIndex();
				}
				if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
					passwordIndex = cell.getColumnIndex();
				}
			}

			lastRowIndex = sheet.getLastRowNum();

			for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
				rowData = sheet.getRow(rowIndex);
				loginApiRequest = new LoginApiRequest(rowData.getCell(userNameIndex).toString(),
						rowData.getCell(passwordIndex).toString());
				userList.add(loginApiRequest);
			}

			xssfWorkbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();

	}

}
