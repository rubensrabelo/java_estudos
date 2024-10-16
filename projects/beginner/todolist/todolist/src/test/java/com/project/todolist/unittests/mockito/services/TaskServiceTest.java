package com.project.todolist.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.project.todolist.services.exceptions.RequiredObjectIsNullException;
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
		List<Task> list = input.mockEntityList(9);
		
		when(repository.findAll()).thenReturn(list);

		var tasks = service.findAll();
		
		assertNotNull(tasks);
		assertEquals(9, tasks.size());
		
		var taskOne = tasks.get(1);

		assertNotNull(taskOne);
		assertNotNull(taskOne.getKey());
		assertNotNull(taskOne.getLinks());
		
		assertTrue(taskOne.toString().contains("links: [</api/tasks/v1/1>;rel=\"self\"]"));

		assertEquals("Task 1", taskOne.getName());
		assertEquals("Description 1", taskOne.getDescription());
		assertEquals(TaskStatus.PENDING, taskOne.getTaskStatus());
		
		var taskSix = tasks.get(6);
		
		assertNotNull(taskSix);
		assertNotNull(taskSix.getKey());
		assertNotNull(taskSix.getLinks());
		
		assertTrue(taskSix.toString().contains("links: [</api/tasks/v1/6>;rel=\"self\"]"));
		
		assertEquals("Task 6", taskSix.getName());
		assertEquals("Description 6", taskSix.getDescription());
		assertEquals(TaskStatus.COMPLETED, taskSix.getTaskStatus());
		
		var taskEight = tasks.get(8);
		
		assertNotNull(taskEight);
		assertNotNull(taskEight.getKey());
		assertNotNull(taskEight.getLinks());
		
		assertTrue(taskEight.toString().contains("links: [</api/tasks/v1/8>;rel=\"self\"]"));
		
		assertEquals("Task 8", taskEight.getName());
		assertEquals("Description 8", taskEight.getDescription());
		assertEquals(TaskStatus.IN_PROGRESS, taskEight.getTaskStatus());
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
		List<Task> list = input.mockEntityList(7);
		
		List<Task> tasksCompleted = list.stream()
				.filter(t -> t.getTaskStatus() == TaskStatus.IN_PROGRESS)
				.collect(Collectors.toList());
		
		when(repository.findByTaskStatus(TaskStatus.IN_PROGRESS.getCode())).thenReturn(tasksCompleted);
		
		List<TaskVO> results = service.findByTaskStatus(TaskStatus.IN_PROGRESS);
		
		assertFalse(results.isEmpty());
		
		TaskVO taskOne = results.get(0);
		
		assertNotNull(taskOne);
		assertNotNull(taskOne.getKey());
		assertNotNull(taskOne.getLinks());
		
		assertTrue(taskOne.toString().contains("links: [</api/tasks/v1/2>;rel=\"self\"]"));
		
		assertEquals("Task 2", taskOne.getName());
	    assertEquals("Description 2", taskOne.getDescription());
	    assertEquals(TaskStatus.IN_PROGRESS, taskOne.getTaskStatus());
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
	void testInsertWithNullTask() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.insert(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
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
	
	@Test
	void testUpdateWithNullTask() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(1L, null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
}
