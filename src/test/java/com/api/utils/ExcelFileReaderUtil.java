package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelFileReaderUtil {
	
	private ExcelFileReaderUtil() {
		// private constructor to prevent instantiation
	}
	
	public static <T> Iterator<T> loadExcel(String excelFilePath, String sheetName, Class<T> bean) {
		InputStream inputStream;
		XSSFWorkbook xssfWorkbook;
		XSSFSheet sheet = null;

		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelFilePath);
		try {
			xssfWorkbook = new XSSFWorkbook(inputStream);
			sheet = xssfWorkbook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<T> list = Poiji.fromExcel(sheet, bean);
		return list.iterator();

	}

}
