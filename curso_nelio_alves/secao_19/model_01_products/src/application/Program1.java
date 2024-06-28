package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Product;
import services.CalculationService;

public class Program1 {

	public static void main(String[] args) {
		File file = new File("C:\\projetos_java\\curso_nelio_alves\\secao_19\\products.csv");
		
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
			
			Product product = CalculationService.max(products); 
			
			System.out.println("Most expensive:");
			System.out.println(product);
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
