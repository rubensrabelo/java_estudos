package application;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import model.entities.Product;
import model.utils.SumPricePredicate;

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
		
		 Predicate<Product> removeCondition = new SumPricePredicate();
		
		list.removeIf(removeCondition);
		
		list.forEach(System.out::println);
		
		Double sumPrice = list.stream()
				.mapToDouble(Product::getPrice)
				.reduce(0, Double::sum);
		
		System.out.println("The sum of products starting with T is " + sumPrice);
	}

}
