package com.project.todolist.unittests.mockito.services;

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
		Task task = input.mockEntity();
		task.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(task));
		
		var result = service.findById(1L);
	}

	@Test
	void testFindByTaskStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

}
