package com.project.todolist.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.models.Task;
import com.project.todolist.unittest.mapper.mocks.MockTask;

public class DozerConverterTest {

	MockTask inputObjects;

	@BeforeEach
	public void setup() {
		inputObjects = new MockTask();
	}

	@Test
	public void parseEntityToVOTest() {
		TaskVO output = DozerMapper.parseObject(inputObjects.mockEntity(), TaskVO.class);

		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("Task 0", output.getName());
		assertEquals("Description 0", output.getDescription());
		assertEquals(TaskStatus.COMPLETED, output.getTaskStatus());

		assertNotNull(output.getCreatedAt());
		assertNotNull(output.getUpdatedAt());

		LocalDateTime now = LocalDateTime.now();

		assertTrue(output.getCreatedAt().isBefore(now.plusSeconds(1))
				&& output.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(output.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& output.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(output.getCreatedAt(), output.getUpdatedAt());
	}

	@Test
	public void parseEntityListToVOListTest() {
		List<TaskVO> tasks = DozerMapper.parseListObjects(inputObjects.mockEntityList(8), TaskVO.class);

		TaskVO taskOne = tasks.get(1);

		assertEquals(Long.valueOf(1L), taskOne.getKey());
		assertEquals("Task 1", taskOne.getName());
		assertEquals("Description 1", taskOne.getDescription());
		assertEquals(TaskStatus.PENDING, taskOne.getTaskStatus());

		assertNotNull(taskOne.getCreatedAt());
		assertNotNull(taskOne.getUpdatedAt());

		LocalDateTime now = LocalDateTime.now();

		assertTrue(taskOne.getCreatedAt().isBefore(now.plusSeconds(1))
				&& taskOne.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(taskOne.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& taskOne.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(taskOne.getCreatedAt(), taskOne.getUpdatedAt());

		TaskVO taskSix = tasks.get(6);

		assertEquals(Long.valueOf(6L), taskSix.getKey());
		assertEquals("Task 6", taskSix.getName());
		assertEquals("Description 6", taskSix.getDescription());
		assertEquals(TaskStatus.COMPLETED, taskSix.getTaskStatus());

		assertNotNull(taskSix.getCreatedAt());
		assertNotNull(taskSix.getUpdatedAt());

		assertTrue(taskSix.getCreatedAt().isBefore(now.plusSeconds(1))
				&& taskSix.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(taskSix.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& taskSix.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(taskSix.getCreatedAt(), taskSix.getUpdatedAt());

		TaskVO taskSeven = tasks.get(7);

		assertEquals(Long.valueOf(7L), taskSeven.getKey());
		assertEquals("Task 7", taskSeven.getName());
		assertEquals("Description 7", taskSeven.getDescription());
		assertEquals(TaskStatus.PENDING, taskSeven.getTaskStatus());

		assertNotNull(taskSeven.getCreatedAt());
		assertNotNull(taskSeven.getUpdatedAt());

		assertTrue(taskSeven.getCreatedAt().isBefore(now.plusSeconds(1))
				&& taskSeven.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(taskSeven.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& taskSeven.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(taskSeven.getCreatedAt(), taskSeven.getUpdatedAt());
	}

	@Test
	public void parseVOToEntityTest() {
		Task output = DozerMapper.parseObject(inputObjects.mockVO(), Task.class);

		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("Task 0", output.getName());
		assertEquals("Description 0", output.getDescription());
		assertEquals(TaskStatus.COMPLETED, output.getTaskStatus());
		
		
		assertNotNull(output.getCreatedAt());
		assertNotNull(output.getUpdatedAt());

		LocalDateTime now = LocalDateTime.now();

		assertTrue(output.getCreatedAt().isBefore(now.plusSeconds(1))
				&& output.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(output.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& output.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(output.getCreatedAt(), output.getUpdatedAt());		
	}

	@Test
	public void parseVOListToEntityList() {
		List<Task> tasks = DozerMapper.parseListObjects(inputObjects.mockVOList(8), Task.class);

		Task taskOne = tasks.get(1);

		assertEquals(Long.valueOf(1L), taskOne.getId());
		assertEquals("Task 1", taskOne.getName());
		assertEquals("Description 1", taskOne.getDescription());
		assertEquals(TaskStatus.PENDING, taskOne.getTaskStatus());

		assertNotNull(taskOne.getCreatedAt());
		assertNotNull(taskOne.getUpdatedAt());

		LocalDateTime now = LocalDateTime.now();

		assertTrue(taskOne.getCreatedAt().isBefore(now.plusSeconds(1))
				&& taskOne.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(taskOne.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& taskOne.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(taskOne.getCreatedAt(), taskOne.getUpdatedAt());

		Task taskSix = tasks.get(6);

		assertEquals(Long.valueOf(6L), taskSix.getId());
		assertEquals("Task 6", taskSix.getName());
		assertEquals("Description 6", taskSix.getDescription());
		assertEquals(TaskStatus.COMPLETED, taskSix.getTaskStatus());

		assertNotNull(taskSix.getCreatedAt());
		assertNotNull(taskSix.getUpdatedAt());

		assertTrue(taskSix.getCreatedAt().isBefore(now.plusSeconds(1))
				&& taskSix.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(taskSix.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& taskSix.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(taskSix.getCreatedAt(), taskSix.getUpdatedAt());

		Task taskSeven = tasks.get(7);

		assertEquals(Long.valueOf(7L), taskSeven.getId());
		assertEquals("Task 7", taskSeven.getName());
		assertEquals("Description 7", taskSeven.getDescription());
		assertEquals(TaskStatus.PENDING, taskSeven.getTaskStatus());

		assertNotNull(taskSeven.getCreatedAt());
		assertNotNull(taskSeven.getUpdatedAt());

		assertTrue(taskSeven.getCreatedAt().isBefore(now.plusSeconds(1))
				&& taskSeven.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(taskSeven.getUpdatedAt().isBefore(now.plusSeconds(1))
				&& taskSeven.getUpdatedAt().isAfter(now.minusSeconds(1)));

		assertEquals(taskSeven.getCreatedAt(), taskSeven.getUpdatedAt());
	}
}
