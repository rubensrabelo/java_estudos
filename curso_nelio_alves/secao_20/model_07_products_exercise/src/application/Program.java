package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_20\\products.csv";
		File file = new File(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			
			line = br.readLine();
			
			List<Product> product = new ArrayList<>();
			
			while(line != null) {
				
				
				line = br.readLine();
			}
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
