package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Departament;
import model.entities.HourContract;
import model.entities.Worker;
import model.entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter departament's name: ");
		String departament_str = sc.nextLine();
		
		Departament departament = new Departament(departament_str);
		
		System.out.println("Enter worker data: ");
		System.out.println("Name");
		String name = sc.nextLine();
		
		System.out.println("level: ");
		String level_str = sc.nextLine().toUpperCase();
		
		WorkerLevel level = WorkerLevel.valueOf(level_str);
		
		System.out.println("Base Salary: ");
		Double value = sc.nextDouble();
		
		Worker worker = new Worker(name, level, value, departament);
		
		System.out.println("How many contracts to this worker? ");
		int qtd = sc.nextInt();
		
		for(int i = 0; i<qtd; i++) {
			sc.nextLine();
			
			System.out.println("Date (DD/MM/YYYY): ");
			String date_str = sc.nextLine();
			LocalDate date = LocalDate.parse(date_str, fmt);
			
			System.out.println("Value per hour: ");
			value = sc.nextDouble();
			
			System.out.println("Duration (hour)");
			int duration = sc.nextInt();
			
			HourContract contract = new HourContract(date, value, duration);
			
			worker.addContracts(contract);
		}
		
		sc.nextLine();
		System.out.println();
		System.out.println("Enter moth and year to calculate income (MM/YYYY): ");
		String monthYear = sc.nextLine();
		
		String[] data = monthYear.split("/");
		int month = Integer.parseInt(data[0]);
		int year = Integer.parseInt(data[1]);
		
		System.out.println();
		System.out.println(worker);
		
		System.out.println("Income for " + monthYear + ": " 
		+ String.format("%.2f", worker.income(year, month)));
		
		sc.close();
	}

}
