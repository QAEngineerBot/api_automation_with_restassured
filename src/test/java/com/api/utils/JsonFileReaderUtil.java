package com.api.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileReaderUtil {
	
	public static <T> Iterator<T> loadJson(String jsonFilePath,Class<T[]> pojoClass) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonFilePath);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] loginRequest;
		List<T> list = null;
		
		try {
			loginRequest=objectMapper.readValue(inputStream, pojoClass);
			list = Arrays.asList(loginRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list.iterator();
	}

}
