package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobApiRequest;
import static com.api.utils.CreateJobAPIMapper.*;
import com.api.utils.CsvFileReaderUtil;
import com.data.beans.CreateJobBean;
import com.data.beans.LoginRequestBean;

public class DataProviderUtils {
	
	@DataProvider(name = "LoginDataProvider",parallel = true)
	public static Iterator<LoginRequestBean> loginApiDataProvider() {
		return CsvFileReaderUtil.loadCsv("test-data/LoginCreds.csv",LoginRequestBean.class);
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

}
