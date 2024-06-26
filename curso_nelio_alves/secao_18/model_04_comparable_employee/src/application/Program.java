package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		File path = new File("C:\\projetos_java\\curso_nelio_alves\\secao_18\\funcionarios.csv");
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			
			line = br.readLine();
			
			List<Employee> employees = new ArrayList<>();
			
			while(line != null) {
				String[] datas = line.split(",");
				String fullName = datas[0];
				Double salary = Double.parseDouble(datas[1]);
				
				Employee employee = new Employee(fullName, salary);
				
				employees.add(employee);
				
				line = br.readLine();
			}
			
			employees.sort(null);
			
			for(Employee employee : employees)
				System.out.println(employee);
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
