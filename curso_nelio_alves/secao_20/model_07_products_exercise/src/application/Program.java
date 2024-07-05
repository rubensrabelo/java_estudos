package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_20\\products.csv";
		File file = new File(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			
			line = br.readLine();
			
			List<Product> products = new ArrayList<>();
			
			while(line != null) {
				String[] datas = line.split(",");
				String name = datas[0];
				Double price = Double.parseDouble(datas[1]);
				
				Product product = new Product(name, price);
				
				products.add(product);
				
				line = br.readLine();
			}
			
			double sumPrices = products.stream()
					.mapToDouble(Product::getPrice)
					.reduce(0.0, Double::sum);
			
			double averagePrice = sumPrices/products.size();
			
			System.out.println("Average = " + String.format("%.2f", averagePrice));
			
			System.out.println();
			
			products.stream()
			.filter(x -> x.getPrice() < averagePrice)
			.map(Product::getName)
			.forEach(System.out::println);
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
