package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Company;
import model.entities.Individual;
import model.entities.TaxPayer;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of tax payers:");
		int qtd = sc.nextInt();
		
		List<TaxPayer> taxPayers = new ArrayList<>();
		
		for(int i = 0; i < qtd; i++) {
			System.out.println("Tax payer #"+(i+1)+" data:");
			System.out.println("Individual or company (i/c)?");
			char type = sc.next().toLowerCase().charAt(0);
			
			sc.nextLine();
			System.out.println("Name:");
			String name = sc.nextLine();
			System.out.println("Anual income:");
			Double anualIncome = sc.nextDouble();
			
			if(type == 'c') {
				System.out.println("Number of employees:");
				int numberOfEmployees = sc.nextInt();
				
				Company company = new Company(name, anualIncome, numberOfEmployees);
				
				taxPayers.add(company);
			} else {
				System.out.println("Health expenditures:");
				Double healthExpenditures = sc.nextDouble();
				
				Individual individual = new Individual(name, anualIncome, healthExpenditures);
				
				taxPayers.add(individual);
			}
		}
		
		double sumTax = 0.0;
		System.out.println();
		System.out.println("TAXES PAID:");
		
		for(TaxPayer taxPayer : taxPayers) {
			System.out.println(taxPayer);
			sumTax += taxPayer.tax();
		}
		
		System.out.println();
		System.out.println("TOTAL TAXES: " + String.format("%.2f", sumTax));
			
		
		sc.close();
	}

}
