package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Course {
	public String name;
	
	public List<Integer> idStudents = new ArrayList<>();
	
	public Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getIdStudents() {
		return idStudents;
	}

	public void addIdStudents(Integer idStudent) {
		this.idStudents.add(idStudent);
	}
}
