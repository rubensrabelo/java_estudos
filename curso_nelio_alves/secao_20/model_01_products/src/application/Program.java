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
		
		System.out.println();
		
		List<Product> list2 = new ArrayList<>();
		
		list2.add(new Product("TV 2", 900.00));
		list2.add(new Product("Notebook 2", 1200.00));
		list2.add(new Product("Tablet 2", 450.00));
		
		list2.sort(Program::compareTo);
		
		list2.sort((prod1, prod2) -> prod1.getPrice().compareTo(prod2.getPrice()));
		
		list2.forEach(System.out::println);
		
	}

}
