package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Employee;
import model.entities.OutsourcedEmployee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of employees:");
		int qtd = sc.nextInt();
		
		List<Employee> employees = new ArrayList<>();
		
		for(int i = 0; i<qtd; i++) {
			System.out.println("Employee #" + (i+1) + " data:");
			System.out.println("Outsourced (y/n)?");
			char option = sc.next().toLowerCase().charAt(0);
			
			sc.nextLine();
			
			System.out.println("Name: ");
			String name = sc.nextLine();
			
			System.out.println("Hours: ");
			Integer hour = sc.nextInt();
			
			System.out.println("Value per hour:");
			Double valuePerHour = sc.nextDouble();
			
			if(option == 'y') {
				System.out.println("Additional charge:");
				Double additionalCharge = sc.nextDouble();
				
				Employee employee = new OutsourcedEmployee(name, hour, valuePerHour, additionalCharge);
				
				employees.add(employee);
			} else {
				Employee employee = new Employee(name, hour, valuePerHour);
				
				employees.add(employee);
			}
		}
		
		for(Employee employee : employees) {
			System.out.println(employee);
		}
		
		sc.close();
	}

}
