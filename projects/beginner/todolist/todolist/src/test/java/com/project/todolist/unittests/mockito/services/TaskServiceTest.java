package com.project.todolist.unittests.mockito.services;

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

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.models.Task;
import com.project.todolist.repositories.TaskRepository;
import com.project.todolist.services.TaskService;
import com.project.todolist.unittest.mapper.mocks.MockTask;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	MockTask input;

	@InjectMocks
	private TaskService service;

	@Mock
	TaskRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockTask();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		Task entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/tasks/v1/1>;rel=\"self\"]"));

		assertEquals("Task 1", result.getName());
		assertEquals("Description 1", result.getDescription());
		assertEquals(TaskStatus.PENDING, result.getTaskStatus());
	}

	@Test
	void testFindByTaskStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		Task entity = input.mockEntity(1);

		Task persisted = input.mockEntity(1);
		persisted.setId(1L);

		TaskVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(1L, vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/tasks/v1/1>;rel=\"self\"]"));

		assertEquals("Task 1", result.getName());
		assertEquals("Description 1", result.getDescription());
		assertEquals(TaskStatus.PENDING, result.getTaskStatus());
	}

	@Test
	void testDelete() {
		Task entity = input.mockEntity(1);
		entity.setId(1L);

		service.delete(1L);
	}

	@Test
	void testUpdate() {
		Task entity = input.mockEntity(1);
		entity.setId(1L);

		Task persisted = input.mockEntity(1);
		persisted.setId(1L);

		TaskVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.insert(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/tasks/v1/1>;rel=\"self\"]"));

		assertEquals("Task 1", result.getName());
		assertEquals("Description 1", result.getDescription());
		assertEquals(TaskStatus.PENDING, result.getTaskStatus());
	}

}
