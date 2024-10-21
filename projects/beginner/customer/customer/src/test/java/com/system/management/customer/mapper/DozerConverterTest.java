package com.system.management.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
	public void parseEntityToVO( ) {
		CustomerVO output = DozerMapper.parseObject(input.mockEntity(0), CustomerVO.class);
		
		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("FirstName 0", output.getFirstName());
		assertEquals("LastName", output.getLastName());
		assertEquals("test@test.com", output.getEmail());
		assertEquals("11111-1111", output.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), output.getBirthDate());
	}
	
	@Test
	public void parseVOToEntity( ) {
		Customer output = DozerMapper.parseObject(input.mockVO(0), Customer.class);
		
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("FirstName 0", output.getFirstName());
		assertEquals("LastName", output.getLastName());
		assertEquals("test@test.com", output.getEmail());
		assertEquals("11111-1111", output.getPhoneNumber());
		assertEquals(LocalDate.parse("1995-08-01"), output.getBirthDate());
	}
}
