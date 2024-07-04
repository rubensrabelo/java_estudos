package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.entities.Product;
import model.utils.UpperCaseName;

public class Program {

	public static int compareTo(Product prod01, Product prod02) {
		return prod01.getPrice().compareTo(prod02.getPrice());
	}
	
	public static void main(String[] args) {
		List<Product> list = new ArrayList<>();
		
		list.add(new Product("Tv", 900.00));
		list.add(new Product("Mouse", 50.00));
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));
		
		UpperCaseName upperCaseName = new UpperCaseName();
		
		List<String> names = list.stream()
				.map(upperCaseName::apply)
				.collect(Collectors.toList());
		
		for(String name: names)
			System.out.println(name);
		
		
	}

}
