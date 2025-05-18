package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String authorizationToken;

    @DisplayName("User can be created")
    @Test
    @Order(1)
    void testCreateUser_whenValidDetailsProvided_returnsUserDetails() throws JSONException {

        // Arange
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("firstName", "Sergey");
        userDetailsRequestJson.put("lastName", "Kargopolov");
        userDetailsRequestJson.put("email", "test@test.com");
        userDetailsRequestJson.put("password","12345678");
        userDetailsRequestJson.put("repeatPassword", "12345678");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJson.toString(), headers);

        // Act

        ResponseEntity<UserRest> response = testRestTemplate
                .postForEntity("/users", request, UserRest.class);
        UserRest user = response.getBody();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assert user != null;
        Assertions.assertEquals(userDetailsRequestJson.getString("firstName"),
                user.getFirstName(),
                "Returned user's first name seems to be incorrect");
        Assertions.assertEquals(userDetailsRequestJson.getString("lastName"),
                user.getLastName(),
                "Returned user's last name seems to be incorrect");
        Assertions.assertEquals(userDetailsRequestJson.getString("email"),
                user.getEmail(),
                "Returned user's email seems to be incorrect");
        Assertions.assertFalse(user.getUserId().trim().isEmpty(),
                "User id should not be empty");
    }
}
