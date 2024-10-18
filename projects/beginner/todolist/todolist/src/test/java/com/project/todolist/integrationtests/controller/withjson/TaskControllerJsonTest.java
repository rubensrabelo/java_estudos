package com.project.todolist.integrationtests.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.todolist.configs.TestConfigs;
import com.project.todolist.integrationtests.testcontainers.AbstractIntegrationTest;
import com.project.todolist.integrationtests.vo.TaskVO;

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
		
		task = new TaskVO();
	}
	
	@Test
	@Order(1)
	public void testeCreate() {
		mockTask();
		
		var content = 
				given()
					.basePath("/swagger-ui/index.html")
					.port(TestConfigs.SERVER_PORT)
					.when()
						.get()
					.then()
						.statusCode(200)
					.extract()
						.body()
							.asString();
		assertTrue(content.contains("Swagger UI"));
	}

	private void mockTask() {
		
	}

}
