package com.project.todolist.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.services.TaskService;
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
		
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("Task 0", output.getName());
		assertEquals("Description 0", output.getDescription());
		assertEquals(TaskStatus.COMPLETED, output.getTaskStatus());
		
		assertNotNull(output.getCreatedAt());
		assertNotNull(output.getUpdatedAt());
		
		LocalDateTime now = LocalDateTime.now();
		
		assertTrue(output.getCreatedAt().isBefore(now.plusSeconds(1)) && 
				output.getCreatedAt().isAfter(now.minusSeconds(1)));
		assertTrue(output.getUpdatedAt().isBefore(now.plusSeconds(1)) && 
				output.getUpdatedAt().isAfter(now.minusSeconds(1)));
		
		assertEquals(output.getCreatedAt(), output.getUpdatedAt());
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		
	}
	
	@Test
	public void parseVOToEntityTest() {
		
	}
	
	@Test
	public void parseVOListToEntityList() {
		
	}
}
