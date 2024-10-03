package com.project.todolist.enums;

public enum TaskStatus {
	PENDING(1),
	IN_PROGRESS(2),
	COMPLETED(3);
	
	private int code;
	
	private TaskStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static TaskStatus valueOf(int code) {
		for (TaskStatus status : TaskStatus.values()) {
			if(status.getCode() == code) return status;
		}
		
		throw new IllegalArgumentException("Invalid TaskStatus code");
	}
}
