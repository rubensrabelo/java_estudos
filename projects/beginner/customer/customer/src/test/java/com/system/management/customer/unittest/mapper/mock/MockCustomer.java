package com.system.management.customer.unittest.mapper.mock;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.model.Customer;

public class MockCustomer {

	public Customer mockEntity() {
		return createMockEntity();
	}
	
	public CustomerVO mockVO() {
		return createMockVO();
	}
	
	public Customer createMockEntity() {
		return null;
	}
	
	private CustomerVO createMockVO() {
		return null;
	}
}
