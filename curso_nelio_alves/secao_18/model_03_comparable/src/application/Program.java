package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Person;

public class Program {

	public static void main(String[] args) {
		String pathFile = "C:\\projetos_java\\curso_nelio_alves\\secao_18\\nome_completo.csv";
		File path = new File(pathFile);
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			
			line = br.readLine();
			
			List<Person> persons = new ArrayList<>();
			
			while(line != null) {
				String[] datas = line.split(",");
				String name = datas[0];
				String surname = datas[1];
				
				Person person = new Person(name, surname);
				
				persons.add(person);
				
				line = br.readLine();
			}
			

			persons.sort(null);
			
			for(Person person : persons)
				System.out.println(person);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
