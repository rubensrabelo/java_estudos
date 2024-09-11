package com.course.project.firstProject.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

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

import com.course.project.firstProject.data.vo.v1.PersonVO;
import com.course.project.firstProject.exceptions.RequiredObjectsNullException;
import com.course.project.firstProject.models.Person;
import com.course.project.firstProject.repositories.PersonRepository;
import com.course.project.firstProject.services.PersonService;
import com.course.project.firstProject.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	MockPerson input;

	@InjectMocks
	private PersonService service;

	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByID() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findByID(1L);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\\\"self\\\"]"));

		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstname());
		assertEquals("Last Name Test1", result.getLastname());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreate() {
		
		Person entity = input.mockEntity();

		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\\\"self\\\"]"));

		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstname());
		assertEquals("Last Name Test1", result.getLastname());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity();
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\\\"self\\\"]"));

		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstname());
		assertEquals("Last Name Test1", result.getLastname());
		assertEquals("Female", result.getGender());	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);	}

}
