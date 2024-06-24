package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.println("Numero:");
		Integer number = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Data (dd/MM/yyyy):");
		String dateString = sc.nextLine();
		
		LocalDate date = LocalDate.parse(dateString, fmtDate);
		
		System.out.println("Valor do contrato:");
		Double valueContract = sc.nextDouble();
		
		Contract contract = new Contract(number, date, valueContract);
		
		System.out.println("Entre com o numero de parcelas:");
		Integer quantity = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, quantity);
		
		System.out.println(contract);
		
		// Os calculos estão errados
		
		sc.close();
	}

}
