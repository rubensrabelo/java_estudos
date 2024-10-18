package com.project.todolist.integrationtests.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.todolist.configs.TestConfigs;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.integrationtests.testcontainers.AbstractIntegrationTest;
import com.project.todolist.integrationtests.vo.TaskVO;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TaskControllerJsonTest extends AbstractIntegrationTest {
	
	private static RequestSpecification especification;
	private static ObjectMapper objectMapper;
	
	private static TaskVO task;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.registerModule(new JavaTimeModule());
		
		task = new TaskVO();
	}
	
	@Test
	@Order(1)
	public void testeCreate() throws JsonMappingException, JsonProcessingException {
		mockTask();
		
		especification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "http://localhost:8080")
				.setBasePath("/api/tasks/v1")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(especification)
						.contentType(TestConfigs.CONTENT_TYPE_JSON)
							.body(task)
							.when()
							.post()
						.then()
							.statusCode(201)
						.extract()
							.body()
								.asString();
		
		TaskVO createdTask = objectMapper.readValue(content, TaskVO.class);
		
		task = createdTask;
		
		assertNotNull(createdTask);
		
		assertNotNull(createdTask.getId());
		assertNotNull(createdTask.getName());
		assertNotNull(createdTask.getDescription());
		assertNotNull(createdTask.getTaskStatus());
		assertNotNull(createdTask.getCreatedAt());
		assertNotNull(createdTask.getUpdatedAt());
		assertTrue(createdTask.getId() > 0);
		
		assertEquals("Excel", createdTask.getName());
		assertEquals("Estudar a ferramenta excel", createdTask.getDescription());
		assertEquals(TaskStatus.COMPLETED, createdTask.getTaskStatus());
		
		LocalDateTime now = LocalDateTime.now();
		
		assertTrue(task.getCreatedAt().isBefore(now.plusSeconds(10)));
		assertTrue(task.getCreatedAt().isAfter(now.minusSeconds(10)));
		
		assertTrue(task.getUpdatedAt().isBefore(now.plusSeconds(10)));
		assertTrue(task.getUpdatedAt().isAfter(now.minusSeconds(10)));
	}

	private void mockTask() {
		task.setName("Excel");
		task.setDescription("Estudar a ferramenta excel");
		task.setTaskStatus(TaskStatus.COMPLETED);
	}
}
