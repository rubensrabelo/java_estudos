package com.project.todolist.unittest.mapper.mocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.todolist.data.vo.v1.TaskVO;
import com.project.todolist.enums.TaskStatus;
import com.project.todolist.models.Task;

public class MockTask {
	
	public Task mockEntity() {
		return mockEntity(0);
	}
	
	public TaskVO mockVO() {
		return mockVO(0);
	}
	
	public List<Task> mockEntityList(Integer count) {
		List<Task> tasks = new ArrayList<Task>();
		
		for(int i = 0; i < count; i++) {
			tasks.add(mockEntity(i));
		}
		
		return tasks;
	}
	
	public List<TaskVO> mockVOList(Integer count) {
		List<TaskVO> tasks = new ArrayList<TaskVO>();
		
		for(int i = 0; i < count; i++) {
			tasks.add(mockVO(i));
		}
		
		return tasks;
	}
	
	public Task mockEntity(Integer number) {
		Task task = new Task();
		
		task.setId(number.longValue());
		task.setName("Task " + number);
		task.setDescription("Description " + number);
		
		if(number % 3 == 0) {
			task.setTaskStatus(TaskStatus.COMPLETED);
		} else if(number % 2 == 0 && number % 3 != 0) {
			task.setTaskStatus(TaskStatus.IN_PROGRESS);
		} else {
			task.setTaskStatus(TaskStatus.PENDING);
		}
		
		task.onCreate();
		task.onUpdate();
		
		return task;
	}
	
	public TaskVO mockVO(Integer number) {
		TaskVO taskVO = new TaskVO();
		
		taskVO.setKey(number.longValue());
		taskVO.setName("Task " + number);
		taskVO.setDescription("Description " + number);
		
		if(number % 3 == 0) {
			taskVO.setTaskStatus(TaskStatus.COMPLETED);
		} else if(number % 2 == 0) {
			taskVO.setTaskStatus(TaskStatus.IN_PROGRESS);
		} else {
			taskVO.setTaskStatus(TaskStatus.PENDING);
		}
		
		taskVO.setCreatedAt(LocalDateTime.now());
		taskVO.setUpdatedAt(LocalDateTime.now());
		
		return taskVO;
	}
}
