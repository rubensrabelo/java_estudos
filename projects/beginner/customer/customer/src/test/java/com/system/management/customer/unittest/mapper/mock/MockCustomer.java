package com.system.management.customer.unittest.mapper.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.model.Customer;

public class MockCustomer {

	public Customer mockEntity(Integer number) {
		return createMockEntity(number);
	}
	
	public CustomerVO mockVO(Integer number) {
		return createMockVO(number);
	}
	
	public List<Customer> mockEntityList(Integer number) {
		List<Customer> customers = new ArrayList<>();
		
		for(int i = 0; i < number; i++) {
			customers.add(createMockEntity(number));
		}
		
		return customers;
	}
	
	public List<CustomerVO> mockVOList(Integer number) {
		List<CustomerVO> customers = new ArrayList<>();
		
		for(int i = 0; i < number; i++) {
			customers.add(createMockVO(number));
		}
		
		return customers;
	}
	
	public Customer createMockEntity(Integer number) {
		Customer customer = new Customer();
		
		customer.setId(number.longValue());
		customer.setFirstName("FirstName " + number);
		customer.setLastName("LastName");
		customer.setEmail("test@test.com");
		customer.setPhoneNumber("11111-1111");
		customer.setBirthDate(LocalDate.parse("1995-08-01"));
		
		if(number % 2 == 0)
			customer.setGender("F");
		else
			customer.setGender("M");
		
		return customer;
	}
	
	private CustomerVO createMockVO(Integer number) {
		CustomerVO customer = new CustomerVO();
		
		customer.setKey(number.longValue());
		customer.setFirstName("FirstName " + number);
		customer.setLastName("LastName");
		customer.setEmail("test@test.com");
		customer.setPhoneNumber("11111-1111");
		customer.setBirthDate(LocalDate.parse("1995-08-01"));
		
		if(number % 2 == 0)
			customer.setGender("F");
		else
			customer.setGender("M");
		
		return customer;
	}
}
