package com.project.todolist.controllers;

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

@RestController
@RequestMapping(value = "/api/tasks/v1")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping
	public ResponseEntity<List<TaskVO>> findAll() {
		List<TaskVO> tasks = service.findAll();
		
		return ResponseEntity.ok().body(tasks);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskVO> findById(@PathVariable("id") Long id) {
		TaskVO TaskVO = service.findById(id);
		
		return ResponseEntity.ok().body(TaskVO);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<TaskVO>> findByTaskStatus(@RequestParam(value = "status") String status) {
		TaskStatus taskStatus;
		
		try {
			taskStatus = TaskStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
		
		List<TaskVO> tasks = service.findByTaskStatus(taskStatus);
		
		return ResponseEntity.ok().body(tasks);
	}
	
	@PostMapping
	public ResponseEntity<TaskVO> insert(@RequestBody TaskVO TaskVO) {
		TaskVO = service.insert(TaskVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(TaskVO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(TaskVO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TaskVO> update(@PathVariable("id") Long id, @RequestBody TaskVO TaskVO) {
		TaskVO = service.update(id, TaskVO);
		return ResponseEntity.ok().body(TaskVO);
	}
}
