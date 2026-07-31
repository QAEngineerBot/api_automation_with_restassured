package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.api.request.model.CreateJobApiRequest;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class CreateJobFakerUtils {
	
	private final static Faker faker=new Faker();
	private final static String COUNTRY="India";	
	private final static int PRODUCT_ID=3;
	private final static int MST_MODEL_ID=3;
	private final static Random random = new Random();
	private final static int MST_SERVICE_LOCATION_ID=0;
	private final static int MST_PLATFORM_ID=2;
	private final static int MST_WARRENTY_STATUS_ID=1;
	private final static int MST_OEM_ID=2;
	
	public static CreateJobApiRequest generateFakeCreateJobPayload() {
		Customer customer=generateFakeCustomerPayload();
		CustomerAddress customerAddress= generateFakeCustomerAddressPayload();
		CustomerProduct customerProduct = generateFakeCustomerProductPayload();
		List<Problems> problemsList=generateFakeProblemsPayload();
		CreateJobApiRequest createJobPayload = new CreateJobApiRequest(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
		return createJobPayload;
	}
	
	public static Iterator<CreateJobApiRequest> generateFakeCreateJobPayload(int count) {
		List<CreateJobApiRequest> createJobApiPayloadList = new ArrayList<CreateJobApiRequest>();
		
		for(int i=1;i<=count;i++) {
		Customer customer=generateFakeCustomerPayload();
		CustomerAddress customerAddress= generateFakeCustomerAddressPayload();
		CustomerProduct customerProduct = generateFakeCustomerProductPayload();
		List<Problems> problemsList=generateFakeProblemsPayload();
		CreateJobApiRequest createJobPayload = new CreateJobApiRequest(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
		createJobApiPayloadList.add(createJobPayload);
		}
		return createJobApiPayloadList.iterator();
	}

	private static List<Problems> generateFakeProblemsPayload() {
		Problems problem = new Problems((random.nextInt(25)+1), faker.lorem().sentence(3));
		List<Problems> list = new ArrayList<Problems>();
		list.add(problem);
		return list;
	}

	private static CustomerProduct generateFakeCustomerProductPayload() {
		String imei=faker.numerify("##############");
		return new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), imei, imei, imei, faker.internet().url(),
				PRODUCT_ID, MST_MODEL_ID);
	}

	private static CustomerAddress generateFakeCustomerAddressPayload() {
		return new CustomerAddress(faker.number().digits(3), faker.address().streetName(), 
				faker.address().streetName(), faker.address().streetName(), faker.address().streetName(),
				faker.numerify("4#####"), COUNTRY, 
				faker.address().state());
	}

	private static Customer generateFakeCustomerPayload() {
		
		return new Customer(faker.name().firstName(), faker.name().lastName(), 
				faker.numerify("77########"), faker.numerify("78########"), 
				faker.internet().emailAddress(), faker.internet().emailAddress());
	}

}
