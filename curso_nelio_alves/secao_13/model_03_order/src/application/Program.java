package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Client;
import model.entities.Order;
import model.entities.OrderItem;
import model.entities.Product;
import model.entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt= DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter client data: ");
		
		System.out.println("Name: ");
		String name = sc.nextLine();
		
		System.out.println("Email: ");
		String email = sc.nextLine();
		
		System.out.println("Birth Date (DD/MM/YYYY): ");
		String birthDate_str = sc.nextLine();
		
		LocalDate birthDate = LocalDate.parse(birthDate_str, fmt);
		
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Status: ");
		String status_str = sc.nextLine().toUpperCase();
		
		OrderStatus status = OrderStatus.valueOf(status_str);
		
		Order order = new Order(LocalDateTime.now(), status, client);
		
		System.out.println("How many items to this order?");
		int qtd_order = sc.nextInt();
		
		for(int i = 0; i < qtd_order; i++) {
			sc.nextLine();
			System.out.println("Product name: ");
			name = sc.nextLine();
			System.out.println("Product price:");
			Double price = sc.nextDouble();
			
			Product product = new Product(name, price);
			
			System.out.println("Quantity: ");
			int quantity = sc.nextInt();
			
			OrderItem item = new OrderItem(quantity, product);
			
			order.addItem(item);
		}
		
		System.out.println();
		System.out.println(order);
		
		sc.close();
	}

}
