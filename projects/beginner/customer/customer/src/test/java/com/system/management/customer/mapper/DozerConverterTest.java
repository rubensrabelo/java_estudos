package com.system.management.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.model.Customer;
import com.system.management.customer.unittest.mapper.mock.MockCustomer;

public class DozerConverterTest {

	MockCustomer input;

	@BeforeEach
	public void setup() {
		input = new MockCustomer();
	}

	@Test
	public void parseEntityToVO() {
		CustomerVO customer = DozerMapper.parseObject(input.mockEntity(0), CustomerVO.class);

		assertEquals(Long.valueOf(0L), customer.getKey());
		assertEquals("FirstName 0", customer.getFirstName());
		assertEquals("LastName", customer.getLastName());
		assertEquals("test@test.com", customer.getEmail());
		assertEquals("11111-1111", customer.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customer.getBirthDate());
		assertEquals(customer.getGender(), "F");
	}

	@Test
	public void parseEntityListToVOListTest() {
		List<CustomerVO> customers = DozerMapper.parseListObjects(input.mockEntityList(7), CustomerVO.class);

		CustomerVO customerOne = customers.get(1);

		assertEquals("FirstName 1", customerOne.getFirstName());
		assertEquals("LastName", customerOne.getLastName());
		assertEquals("test@test.com", customerOne.getEmail());
		assertEquals("11111-1111", customerOne.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerOne.getBirthDate());
		assertEquals(customerOne.getGender(), "M");

		CustomerVO customerThree = customers.get(3);

		assertEquals("FirstName 3", customerThree.getFirstName());
		assertEquals("LastName", customerThree.getLastName());
		assertEquals("test@test.com", customerThree.getEmail());
		assertEquals("11111-1111", customerThree.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerThree.getBirthDate());
		assertEquals(customerThree.getGender(), "M");

		CustomerVO customerfour = customers.get(4);

		assertEquals("FirstName 4", customerfour.getFirstName());
		assertEquals("LastName", customerfour.getLastName());
		assertEquals("test@test.com", customerfour.getEmail());
		assertEquals("11111-1111", customerfour.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerfour.getBirthDate());
		assertEquals(customerfour.getGender(), "F");

		CustomerVO customerSix = customers.get(6);

		assertEquals("FirstName 6", customerSix.getFirstName());
		assertEquals("LastName", customerSix.getLastName());
		assertEquals("test@test.com", customerSix.getEmail());
		assertEquals("11111-1111", customerSix.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerSix.getBirthDate());
		assertEquals(customerSix.getGender(), "F");
	}
	
	@Test
	public void parseVOListToEntityList() {
		List<Customer> customers = DozerMapper.parseListObjects(input.mockVOList(7), Customer.class);
		
		Customer customerOne = customers.get(1);
		
		assertEquals("FirstName 1", customerOne.getFirstName());
		assertEquals("LastName", customerOne.getLastName());
		assertEquals("test@test.com", customerOne.getEmail());
		assertEquals("11111-1111", customerOne.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerOne.getBirthDate());
		assertEquals(customerOne.getGender(), "M");
		
		Customer customerThree = customers.get(3);
		
		assertEquals("FirstName 3", customerThree.getFirstName());
		assertEquals("LastName", customerThree.getLastName());
		assertEquals("test@test.com", customerThree.getEmail());
		assertEquals("11111-1111", customerThree.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerThree.getBirthDate());
		assertEquals(customerThree.getGender(), "M");
		
		Customer customerfour = customers.get(4);
		
		assertEquals("FirstName 4", customerfour.getFirstName());
		assertEquals("LastName", customerfour.getLastName());
		assertEquals("test@test.com", customerfour.getEmail());
		assertEquals("11111-1111", customerfour.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerfour.getBirthDate());
		assertEquals(customerfour.getGender(), "F");
		
		Customer customerSix = customers.get(6);
		
		assertEquals("FirstName 6", customerSix.getFirstName());
		assertEquals("LastName", customerSix.getLastName());
		assertEquals("test@test.com", customerSix.getEmail());
		assertEquals("11111-1111", customerSix.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customerSix.getBirthDate());
		assertEquals(customerSix.getGender(), "F");
	}

	@Test
	public void parseVOToEntity() {
		Customer customer = DozerMapper.parseObject(input.mockVO(0), Customer.class);

		assertEquals(Long.valueOf(0L), customer.getId());
		assertEquals("FirstName 0", customer.getFirstName());
		assertEquals("LastName", customer.getLastName());
		assertEquals("test@test.com", customer.getEmail());
		assertEquals("11111-1111", customer.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), customer.getBirthDate());
		assertEquals(customer.getGender(), "F");
	}
}
