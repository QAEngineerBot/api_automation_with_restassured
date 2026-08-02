package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobApiRequest;
import com.api.request.model.LoginApiRequest;

import static com.api.utils.CreateJobAPIMapper.*;

import com.api.utils.CreateJobFakerUtils;
import com.api.utils.CsvFileReaderUtil;
import com.api.utils.ExcelFileReaderUtil;
import com.api.utils.JsonFileReaderUtil;
import com.data.beans.CreateJobBean;
import com.data.beans.LoginRequestBean;

public class DataProviderUtils {
	
	@DataProvider(name = "LoginDataProvider",parallel = true)
	public static Iterator<LoginRequestBean> loginApiDataProvider() {
		return CsvFileReaderUtil.loadCsv("test-data/LoginCreds.csv",LoginRequestBean.class);
	}
	
	@DataProvider(name = "LoginJsonDataProvider",parallel = true)
	public static Iterator<LoginApiRequest> loginApiJsonDataProvider() {
		return JsonFileReaderUtil.loadJson("test-data/LoginCreds.json",LoginApiRequest[].class);
	}
	
	@DataProvider(name = "LoginExcelDataProvider",parallel = true)
	public static Iterator<LoginRequestBean> loginApiExcelDataProvider() {
		return ExcelFileReaderUtil.loadExcel("test-data/LoginCreds.xlsx","LoginTestData",LoginRequestBean.class);
	}
	
	@DataProvider(name = "CreateJobJsonDataProvider",parallel = true)
	public static Iterator<CreateJobApiRequest> CreateJobJsonDataProvider() {
		return JsonFileReaderUtil.loadJson("test-data/CreateJobData.json",CreateJobApiRequest[].class);
	}
	
	@DataProvider(name = "CreateJobDataProvider",parallel = true)
	public static Iterator<CreateJobApiRequest> createJobApiDataProvider() {
		Iterator<CreateJobBean> createJobBean=CsvFileReaderUtil.loadCsv("test-data/CreateJob.csv", CreateJobBean.class);
		List<CreateJobApiRequest> createJobList = new ArrayList<CreateJobApiRequest>();
		
		
		while(createJobBean.hasNext()) {
			createJobList.add(mapper(createJobBean.next()));
		}
		
		return createJobList.iterator();
	}
	
	@DataProvider(name = "CreateJobFakerDataProvider",parallel = true)
	public static Iterator<CreateJobApiRequest> createJobApiFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount","10");
		int count = Integer.parseInt(fakerCount);
		return CreateJobFakerUtils.generateFakeCreateJobPayload(count);
	}

}
