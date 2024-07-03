package application;

import java.util.ArrayList;
import java.util.List;

import model.entities.Product;
import model.utils.ProductConsume;

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
		
		list.forEach(new ProductConsume());
		
		for(Product prod : list)
			System.out.println(prod);
		
		
	}

}
