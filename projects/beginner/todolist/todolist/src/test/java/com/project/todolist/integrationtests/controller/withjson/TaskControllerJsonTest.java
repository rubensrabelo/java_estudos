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
import com.fasterxml.jackson.databind.SerializationFeature;
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
	    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		task = new TaskVO();
	}
	
	@Test
	@Order(1)
	public void testeCreate() throws JsonMappingException, JsonProcessingException {
		mockTask();
		
		especification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_8080)
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
		
		TaskVO persistedTask = objectMapper.readValue(content, TaskVO.class);
		
		task = persistedTask;
		
		assertNotNull(persistedTask);
		
		assertNotNull(persistedTask.getId());
		assertNotNull(persistedTask.getName());
		assertNotNull(persistedTask.getDescription());
		assertNotNull(persistedTask.getTaskStatus());
		assertNotNull(persistedTask.getCreatedAt());
		assertNotNull(persistedTask.getUpdatedAt());
		assertTrue(persistedTask.getId() > 0);
		
		assertEquals("Excel", persistedTask.getName());
		assertEquals("Estudar a ferramenta excel", persistedTask.getDescription());
		assertEquals(TaskStatus.COMPLETED, persistedTask.getTaskStatus());
		
		LocalDateTime now = LocalDateTime.now();
		
		int toleranceInSeconds = 120;
		
		assertTrue(task.getCreatedAt().isBefore(now.plusSeconds(toleranceInSeconds)));
		assertTrue(task.getCreatedAt().isAfter(now.minusSeconds(toleranceInSeconds)));
		
		assertTrue(task.getUpdatedAt().isBefore(now.plusSeconds(toleranceInSeconds)));
		assertTrue(task.getUpdatedAt().isAfter(now.minusSeconds(toleranceInSeconds)));
	}
	
	@Test
	@Order(2)
	public void testeCreateWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
		mockTask();
		
		especification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_5000)
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
					.statusCode(403)
				.extract()
					.body()
						.asString();
		
		
		assertNotNull(content);
		assertEquals("Invalid CORS request", content);
	}
	
	@Test
	@Order(3)
	public void testeFindById() throws JsonMappingException, JsonProcessingException {
		mockTask();
		
		especification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_8080)
				.setBasePath("/api/tasks/v1")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(especification)
						.contentType(TestConfigs.CONTENT_TYPE_JSON)
							.pathParam("id", task.getId())
							.when()
							.get("{id}")
						.then()
							.statusCode(200)
						.extract()
							.body()
								.asString();
		
		TaskVO persistedTask = objectMapper.readValue(content, TaskVO.class);
		
		task = persistedTask;
		
		assertNotNull(persistedTask);
		
		assertNotNull(persistedTask.getId());
		assertNotNull(persistedTask.getName());
		assertNotNull(persistedTask.getDescription());
		assertNotNull(persistedTask.getTaskStatus());
		assertNotNull(persistedTask.getCreatedAt());
		assertNotNull(persistedTask.getUpdatedAt());
		assertTrue(persistedTask.getId() > 0);
		
		assertEquals("Excel", persistedTask.getName());
		assertEquals("Estudar a ferramenta excel", persistedTask.getDescription());
		assertEquals(TaskStatus.COMPLETED, persistedTask.getTaskStatus());
		
		LocalDateTime now = LocalDateTime.now();
		
		int toleranceInSeconds = 120;
		
		assertTrue(persistedTask.getCreatedAt().isBefore(now.plusSeconds(toleranceInSeconds)));
		assertTrue(persistedTask.getCreatedAt().isAfter(now.minusSeconds(toleranceInSeconds)));
		
		assertTrue(persistedTask.getUpdatedAt().isBefore(now.plusSeconds(toleranceInSeconds)));
		assertTrue(persistedTask.getUpdatedAt().isAfter(now.minusSeconds(toleranceInSeconds)));
	}
	
	@Test
	@Order(4)
	public void testeFindByIdWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
		mockTask();
		
		especification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_5000)
				.setBasePath("/api/tasks/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(especification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", task.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();
		
		
		assertNotNull(content);
		
		assertEquals("Invalid CORS request", content);
	}

	private void mockTask() {
		task.setName("Excel");
		task.setDescription("Estudar a ferramenta excel");
		task.setTaskStatus(TaskStatus.COMPLETED);
	}
}
