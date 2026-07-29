package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.data.beans.CreateJobBean;
import com.data.beans.LoginRequestBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvFileReaderUtil {

	public static <T> Iterator<T> loadCsv(String path,Class<T> bean) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inputStreamReader);

		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
				.withType(bean).withIgnoreEmptyLine(true).build();

		List<T> userList = csvToBean.parse();

		return userList.iterator();

	}

}
