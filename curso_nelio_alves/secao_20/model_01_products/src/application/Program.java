package application;

import java.util.ArrayList;
import java.util.List;

import model.entities.Product;

public class Program {

	public static int compareTo(Product prod01, Product prod02) {
		return prod01.getPrice().compareTo(prod02.getPrice());
	}
	
	public static void main(String[] args) {
		List<Product> list = new ArrayList<>();
		
		list.add(new Product("TV", 900.00));
		list.add(new Product("Notebook", 1200.00));
		list.add(new Product("Tablet", 450.00));
		
		list.sort(Program::compareTo);
		
		list.forEach(System.out::println);
	}

}
