package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTaxService;
import services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.println("Modelo do carro:");
		String modelString = sc.nextLine();
		
		Vehicle vehicle = new Vehicle(modelString);
		
		System.out.println("Retirada (dd/MM/yyyy hh:mm):");
		String startString = sc.nextLine();
		
		LocalDateTime start = LocalDateTime.parse(startString, fmt); 
		
		System.out.println("Retorno (dd/MM/yyyy hh:mm):");
		String finishString = sc.nextLine();
		
		LocalDateTime finish = LocalDateTime.parse(finishString, fmt);
		
		CarRental car = new CarRental(start, finish, vehicle);
		
		System.out.println("Entre com o preço por hora:");
		Double pricePerHour = sc.nextDouble();
		
		System.out.println("Entre com o preço por dia:");
		Double pricePerDay = sc.nextDouble();
		
		RentalService service = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		service.processInvoice(car);
		
		System.out.println();
		System.out.println("FATURA:");
		System.out.println("Pagamento basico: " + String.format("%.2f", car.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", car.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", car.getInvoice().total()));
		
		sc.close();
	}

}
