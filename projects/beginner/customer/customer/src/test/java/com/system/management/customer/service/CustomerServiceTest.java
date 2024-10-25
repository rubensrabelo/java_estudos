package com.system.management.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.system.management.customer.model.Customer;
import com.system.management.customer.repository.CustomerRepository;
import com.system.management.customer.unittest.mapper.mock.MockCustomer;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	MockCustomer input;
	
	@InjectMocks
	private CustomerService service;
	
	@Mock
	private CustomerRepository repository;
	
	@BeforeEach
	void setUpMock() throws Exception {
		input = new MockCustomer();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Customer> list = input.mockEntityList(5);
		
		when(repository.findAll()).thenReturn(list);
		
		var customers = service.findAll();
		
		assertNotNull(list);
		assertEquals(5, customers.size());
		
		var customerOne = customers.get(1);
		
		assertNotNull(customerOne);
		assertNotNull(customerOne.getKey());
		assertNotNull(customerOne.getLinks());
	}

	@Test
	void testFindById() {
		
	}

	@Test
	void testCreate() {
		
	}

	@Test
	void testDelete() {
		
	}

	@Test
	void testUpdate() {
		
	}

}
