package com.course.course.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.course.data.vo.v1.PersonVO;
import com.course.course.data.vo.v2.PersonVOV2;
import com.course.course.services.PersonService;
import com.course.course.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/persons/v1")
@Tag(name = "Users", description = "Operations related to user management")
public class PersonController {
	
	
	@Autowired
	private PersonService service;
	
	@Operation(summary = "Retrieve all person", description = "Fetch a list of all available person")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of person"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@Operation(summary = "Retrieve a person by ID", description = "Fetch a product by its unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the person"),
        @ApiResponse(responseCode = "404", description = "Person not found")
    })
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.findById(id);
	}
	
	@Operation(summary = "Create a new person", description = "Insert a new person into the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Person successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}
	
	@Operation(summary = "Create a new person", description = "Insert a new Person into the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Person successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
	@PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}
	
	@Operation(summary = "Update a person", description = "Update the details of an existing person")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person successfully updated"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Person not found")
    })
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}
	
	@Operation(summary = "Delete a person", description = "Remove a person from the system by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Person successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Person not found")
    })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
