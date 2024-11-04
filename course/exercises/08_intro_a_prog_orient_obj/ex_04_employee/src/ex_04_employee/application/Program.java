package ex_04_employee.application;

import java.util.Locale;
import java.util.Scanner;

import ex_04_employee.entities.Employee;

public interface Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Name: ");
		String name = sc.nextLine();
		
		System.out.println("Gross Salary: ");
		double salary = sc.nextDouble();
		
		System.out.println("Tax: ");
		double tax = sc.nextDouble();
		
		Employee emp = new Employee(name, salary, tax);
		
		System.out.println();
		System.out.println(emp);
		
		System.out.println();
		System.out.println("Which percentage to increase salary? ");
		double percentage = sc.nextDouble();
		
		emp.increaseSalary(percentage);
		
		System.out.println();
		System.out.println(emp);

		sc.close();
	}
}
