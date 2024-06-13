package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.ImportedProduct;
import model.entities.Product;
import model.entities.UsedProduct;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of products: ");
		int qtd = sc.nextInt();
		
		List<Product> products = new ArrayList<>();
		
		for(int i=0; i<qtd; i++) {
			System.out.println("Product #" + (i+1) + " data:");
			System.out.println("Common, used or imported (c/u/i)?");
			char prodType = sc.next().toLowerCase().charAt(0);
			
			sc.nextLine();
			System.out.println("Name: ");
			String name = sc.nextLine();
			System.out.println("Price: ");
			Double price = sc.nextDouble();
			
			sc.nextLine();
			
			Product product = new Product();
			
			switch (prodType) {
			case 'c':
				product = new Product(name, price);
				break;
			case 'i':
				System.out.println("Customs fee:");
				Double customsFee = sc.nextDouble();
				
				product = new ImportedProduct(name, price, customsFee);
				break;
			case 'u':
				System.out.println("Manufacture date (DD/MM/YYYY):");
				String manufactureDate_str = sc.nextLine();
				
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				LocalDate manufactureDate = LocalDate.parse(manufactureDate_str, fmt);

				product = new UsedProduct(name, price, manufactureDate);
				break;
			default:
				System.out.println("Value invalid!");
				break;
			}
			
			products.add(product);
		}
		
		for(Product product : products)
			System.out.println(product.priceTag());
		
		sc.close();
	}

}
