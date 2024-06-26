package model.entities;

public class Employee implements Comparable<Employee> {
	private String fullName;
	private Double salary;
	
	public Employee() {
	}

	public Employee(String fullName, Double salary) {
		this.fullName = fullName;
		this.salary = salary;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@Override
	public int compareTo(Employee otherEmployee) {
		return getFullName().compareTo(otherEmployee.getFullName());
	}
	
	@Override
	public String toString() {
		return "fullName = " + fullName + ", salary = " + salary;
	}
}
