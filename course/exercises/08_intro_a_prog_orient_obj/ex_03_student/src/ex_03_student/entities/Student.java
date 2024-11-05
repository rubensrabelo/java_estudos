package ex_03_student.entities;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private String name;
	
	private List<Double> grades = new ArrayList<>();
	
	public Student() {
	}
	
	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Double> getGrades() {
		return grades;
	}
	
	public void addGrade(Double value) {
		grades.add(value);
	}
	
	public double average() {
		Double sum = 0.0;
		
		for(Double grade : grades) {
			sum += grade;
		}
		
		return sum;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", grades=" + grades + "]";
	}
}
