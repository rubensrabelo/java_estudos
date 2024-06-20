package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.entities.Item;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_16";
		String filePath = path + "\\items.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line = br.readLine();
			
			List<Item> items = new ArrayList<>();
			
			line = br.readLine();
			
			while(line != null) {
				String[] datas = line.split(",");
				String name = datas[0];
				Double unitPrice = Double.parseDouble(datas[1]);
				Integer quantity = Integer.parseInt(datas[2]);
				
				Item item = new Item(name, unitPrice, quantity);
				
				items.add(item);
				
				line = br.readLine();
			}
			
			for(Item item : items) {
				System.out.println(item);
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
