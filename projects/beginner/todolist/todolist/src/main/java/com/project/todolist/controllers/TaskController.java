package com.project.todolist.controllers;

import static com.project.todolist.util.MediaType.APPLICATION_JSON;
import static com.project.todolist.util.MediaType.APPLICATION_XML;
import static com.project.todolist.util.MediaType.APPLICATION_YML;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.services.TaskService;
import com.project.todolist.services.exceptions.InvalidTaskStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/tasks/v1")
@Tag(name = "Tasks", description = "Endpoints for Managing Tasks")
public class TaskController {

	@Autowired
	private TaskService service;

	@GetMapping(produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
	@Operation(summary = "Find all Tasks", description = "Finds all Tasks", tags = { "Tasks" }, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskVO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<List<TaskVO>> findAll() {
		List<TaskVO> tasks = service.findAll();

		return ResponseEntity.ok().body(tasks);
	}

	@GetMapping(value = "/{id}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
	@Operation(summary = "Finds a Task", description = "Finds a Task", tags = { "Tasks" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<TaskVO> findById(@PathVariable("id") Long id) {
		TaskVO TaskVO = service.findById(id);

		return ResponseEntity.ok().body(TaskVO);
	}

	@GetMapping(value = "/search", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
	@Operation(summary = "Find a Tasks by Status", description = "Finds all Tasks by Status", tags = {
			"Tasks" }, responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskVO.class))) }),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<List<TaskVO>> findByTaskStatus(@RequestParam(value = "status") String status) {
		TaskStatus taskStatus;

		try {
			taskStatus = TaskStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidTaskStatusException(e.getMessage());
		}

		List<TaskVO> tasks = service.findByTaskStatus(taskStatus);

		return ResponseEntity.ok().body(tasks);
	}

	@PostMapping(consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML }, produces = { APPLICATION_JSON,
			APPLICATION_XML, APPLICATION_YML })
	@Operation(summary = "Adds a new Task", description = "Adds a new Task by passing in a JSON, XML or YML representation of the task!", tags = {
			"Tasks" }, responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<TaskVO> insert(@RequestBody TaskVO TaskVO) {
		TaskVO = service.insert(TaskVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(TaskVO.getKey())
				.toUri();

		return ResponseEntity.created(uri).body(TaskVO);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Task", description = "Deletes a Task by passing in a JSON, XML or YML representation of the task!", tags = {
			"Tasks" }, responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}", consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML }, produces = {
			APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
	@Operation(summary = "Updates a Task", description = "Updates a Task by passing in a JSON, XML or YML representation of the task!", tags = {
			"Tasks" }, responses = {
					@ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<TaskVO> update(@PathVariable("id") Long id, @RequestBody TaskVO TaskVO) {
		TaskVO = service.update(id, TaskVO);
		return ResponseEntity.ok().body(TaskVO);
	}
}
