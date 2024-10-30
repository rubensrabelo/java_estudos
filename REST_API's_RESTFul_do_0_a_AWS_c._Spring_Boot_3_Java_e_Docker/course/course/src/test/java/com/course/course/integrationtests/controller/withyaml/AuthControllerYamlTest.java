package com.course.course.integrationtests.controller.withyaml;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.course.course.config.TestConfigs;
import com.course.course.data.vo.v1.security.AccountCredentialsVO;
import com.course.course.data.vo.v1.security.TokenVO;
import com.course.course.integrationtests.controller.withyaml.mapper.YAMLMapper;
import com.course.course.integrationtests.testcontainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class AuthControllerYamlTest extends AbstractIntegrationTest {

	private YAMLMapper objectMapper;
	private static TokenVO tokenVO;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new YMLMapper();
	}
	
	@Test
	@Order(1)
	public void testSignin() throws JsonMappingException, JsonProcessingException {

		AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin123");

		tokenVO = given()
				.spec(specification)
				.config(
						RestAssuredConfig
							.config()
							.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs(
									TestConfigs.CONTENT_TYPE_YML,
									ContentType.TEXT)))
				.accept(TestConfigs.CONTENT_TYPE_YML)
				.basePath("/auth/signin").port(TestConfigs.SERVER_PORT)
				.contentType(TestConfigs.CONTENT_TYPE_YML).body(user).when().post().then().statusCode(200).extract()
				.body(user, objectMapper).as(TokenVO.class, objectMapper);

		assertNotNull(tokenVO.getAccessToken());
		assertNotNull(tokenVO.getRefreshToken());
	}

	@Test
	@Order(2)
	public void testRefresh() throws JsonMappingException, JsonProcessingException {

		var newTokenVO = given()
				.config(
						RestAssuredConfig
							.config()
							.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs(
									TestConfigs.CONTENT_TYPE_YML,
									ContentType.TEXT)))
				.accept(TestConfigs.CONTENT_TYPE_YML)
				.basePath("/auth/refresh").port(TestConfigs.SERVER_PORT)
				.contentType(TestConfigs.CONTENT_TYPE_YML).pathParam("username", tokenVO.getUsername())
				.header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO.getRefreshToken()).when()
				.put("{username}").then().statusCode(200).extract().body().as(TokenVO.class, objectMapper);

		assertNotNull(newTokenVO.getAccessToken());
		assertNotNull(newTokenVO.getRefreshToken());
	}
}
