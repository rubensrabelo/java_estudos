package com.system.management.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.model.Customer;
import com.system.management.customer.repository.CustomerRepository;
import com.system.management.customer.service.exceptions.RequiredObjectIsNullException;
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

		assertTrue(customerOne.toString().contains("links: [</api/customers/v1/1>;rel=\"self\"]"));

		assertEquals("FirstName 1", customerOne.getFirstName());
		assertEquals("LastName", customerOne.getLastName());
		assertEquals("test@test.com", customerOne.getEmail());
		assertEquals("11111-1111", customerOne.getPhoneNumber());
		assertEquals("M", customerOne.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), customerOne.getBirthDate());

		var customerTwo = customers.get(2);

		assertNotNull(customerTwo);
		assertNotNull(customerTwo.getKey());
		assertNotNull(customerTwo.getLinks());

		assertTrue(customerTwo.toString().contains("links: [</api/customers/v1/2>;rel=\"self\"]"));

		assertEquals("FirstName 2", customerTwo.getFirstName());
		assertEquals("LastName", customerTwo.getLastName());
		assertEquals("test@test.com", customerTwo.getEmail());
		assertEquals("11111-1111", customerTwo.getPhoneNumber());
		assertEquals("F", customerTwo.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), customerTwo.getBirthDate());

		var customerThree = customers.get(3);

		assertNotNull(customerThree);
		assertNotNull(customerThree.getKey());
		assertNotNull(customerThree.getLinks());

		assertTrue(customerThree.toString().contains("links: [</api/customers/v1/3>;rel=\"self\"]"));

		assertEquals("FirstName 3", customerThree.getFirstName());
		assertEquals("LastName", customerThree.getLastName());
		assertEquals("test@test.com", customerThree.getEmail());
		assertEquals("11111-1111", customerThree.getPhoneNumber());
		assertEquals("M", customerThree.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), customerThree.getBirthDate());

		var customerFour = customers.get(4);

		assertNotNull(customerFour);
		assertNotNull(customerFour.getKey());
		assertNotNull(customerFour.getLinks());

		assertTrue(customerFour.toString().contains("links: [</api/customers/v1/4>;rel=\"self\"]"));

		assertEquals("FirstName 4", customerFour.getFirstName());
		assertEquals("LastName", customerFour.getLastName());
		assertEquals("test@test.com", customerFour.getEmail());
		assertEquals("11111-1111", customerFour.getPhoneNumber());
		assertEquals("F", customerFour.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), customerFour.getBirthDate());
	}

	@Test
	void testFindById() {
		Customer entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/customers/v1/1>;rel=\"self\"]"));

		assertEquals("FirstName 1", result.getFirstName());
		assertEquals("LastName", result.getLastName());
		assertEquals("test@test.com", result.getEmail());
		assertEquals("11111-1111", result.getPhoneNumber());
		assertEquals("M", result.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), result.getBirthDate());
	}

	@Test
	void testCreate() {
		Customer entity = input.mockEntity(1);

		Customer persisted = input.mockEntity(1);
		persisted.setId(1L);

		CustomerVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/customers/v1/1>;rel=\"self\"]"));

		assertEquals("FirstName 1", result.getFirstName());
		assertEquals("LastName", result.getLastName());
		assertEquals("test@test.com", result.getEmail());
		assertEquals("11111-1111", result.getPhoneNumber());
		assertEquals("M", result.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), result.getBirthDate());
	}
	
	@Test
	void testCreateWithNullCustomer() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Customer entity = input.mockEntity(1);
		entity.setId(1L);

		service.delete(1L);
	}

	@Test
	void testUpdate() {
		Customer entity = input.mockEntity(1);

		Customer persisted = input.mockEntity(1);
		persisted.setId(1L);

		CustomerVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(1L, vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/customers/v1/1>;rel=\"self\"]"));

		assertEquals("FirstName 1", result.getFirstName());
		assertEquals("LastName", result.getLastName());
		assertEquals("test@test.com", result.getEmail());
		assertEquals("11111-1111", result.getPhoneNumber());
		assertEquals("M", result.getGender());
		assertEquals(LocalDate.parse("1995-08-01"), result.getBirthDate());
	}
	
	@Test
	void testUpdateWithNullCustomer() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(1L, null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
}
