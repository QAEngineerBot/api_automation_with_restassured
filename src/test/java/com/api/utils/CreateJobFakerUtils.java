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

	private final static Faker FAKER = new Faker();
	private final static String COUNTRY = "India";
	private final static int PRODUCT_ID = 3;
	private final static int MST_MODEL_ID = 3;
	private final static Random RANDOM = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 0;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRENTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 2;
	private final static int VALID_PROBLEM_ID[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	public static CreateJobApiRequest generateFakeCreateJobPayload() {
		Customer customer = generateFakeCustomerPayload();
		CustomerAddress customerAddress = generateFakeCustomerAddressPayload();
		CustomerProduct customerProduct = generateFakeCustomerProductPayload();
		List<Problems> problemsList = generateFakeProblemsPayload();
		CreateJobApiRequest createJobPayload = new CreateJobApiRequest(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
		return createJobPayload;
	}

	public static Iterator<CreateJobApiRequest> generateFakeCreateJobPayload(int count) {
		String fakerCount = System.getProperty("fakerCount",String.valueOf(count));
		count = Integer.parseInt(fakerCount);
		List<CreateJobApiRequest> createJobApiPayloadList = new ArrayList<CreateJobApiRequest>();
		Customer customer;
		CustomerAddress customerAddress;
		CustomerProduct customerProduct;
		List<Problems> problemsList;
		CreateJobApiRequest createJobPayload;

		for (int i = 1; i <= count; i++) {
			customer = generateFakeCustomerPayload();
			customerAddress = generateFakeCustomerAddressPayload();
			customerProduct = generateFakeCustomerProductPayload();
			problemsList = generateFakeProblemsPayload();
			createJobPayload = new CreateJobApiRequest(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTY_STATUS_ID,
					MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
			createJobApiPayloadList.add(createJobPayload);
		}
		return createJobApiPayloadList.iterator();
	}

	private static List<Problems> generateFakeProblemsPayload() {
		int count = RANDOM.nextInt(3) + 1;
		int index;
		List<Problems> list = new ArrayList<Problems>();
		Problems problem;
		for (int i = 1; i <= count; i++) {
			index = RANDOM.nextInt(VALID_PROBLEM_ID.length);
			problem = new Problems(VALID_PROBLEM_ID[index], FAKER.lorem().sentence(3));
			list.add(problem);
		}
		return list;
	}

	private static CustomerProduct generateFakeCustomerProductPayload() {
		String imei = FAKER.numerify("##############");
		return new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), imei, imei, imei, FAKER.internet().url(),
				PRODUCT_ID, MST_MODEL_ID);
	}

	private static CustomerAddress generateFakeCustomerAddressPayload() {
		return new CustomerAddress(FAKER.number().digits(3), FAKER.address().streetName(), FAKER.address().streetName(),
				FAKER.address().streetName(), FAKER.address().streetName(), FAKER.numerify("4#####"), COUNTRY,
				FAKER.address().state());
	}

	private static Customer generateFakeCustomerPayload() {

		return new Customer(FAKER.name().firstName(), FAKER.name().lastName(), FAKER.numerify("77########"),
				FAKER.numerify("78########"), FAKER.internet().emailAddress(), FAKER.internet().emailAddress());
	}

}
