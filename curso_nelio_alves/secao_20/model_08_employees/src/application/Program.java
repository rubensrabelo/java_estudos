package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import models.entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_20\\employees.csv";
		File file = new File(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			
			line = br.readLine();
			
			List<Employee> employees = new ArrayList<>();
			
			while(line != null) {
				String[] datas = line.split(",");
				
				String name = datas[0];
				String email = datas[1];
				Double salary = Double.parseDouble(datas[2]);
				
				Employee employee = new Employee(name, email, salary);
				
				employees.add(employee);
				
				line = br.readLine();
			}
			
			Double sumSalary = employees.stream()
					.mapToDouble(Employee::getSalary)
					.reduce(0, Double::sum);
			
			Double averageSalary = sumSalary / employees.size();
			
			System.out.println("The employee's names whose salary is higher than average:");
			
			employees.stream()
			.filter(employee -> employee.getSalary() > averageSalary)
			.map(Employee::getName)
			.sorted()
			.forEach(System.out::println);
			
			System.out.println();
			
			Double sumFilter = employees.stream()
			.filter(employee -> employee.getName().charAt(0) == 'M')
			.mapToDouble(Employee::getSalary)
			.reduce(0, Double::sum);
			
			System.out.println("Sum of salary of people whose name starts with 'M':");
			System.out.println(sumFilter);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
