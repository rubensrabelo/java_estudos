package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	
	List<Double> grades = new ArrayList<>();
	
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

	public void addGrades(Double grade) {
		this.grades.add(grade);
	}
	
	public Double finalGrade() {
		double value = 0.0;
		
		for(Double grade : this.grades) {
			value += grade;
		}
		
		return value;
	}
	
	public Boolean status() {
		if (finalGrade() < 60) return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("FINAL GRADE = " + finalGrade() + "\n");
		str.append(status() ? "PASS" : "FAILED");
		
		if(finalGrade() < 60) {
			double diff = 60 - finalGrade();
			
			str.append("\nMISSING " + diff + " POINTS");
		}
		
		return str.toString();
	}
}
