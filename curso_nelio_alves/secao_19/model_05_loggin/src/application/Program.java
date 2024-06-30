package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import model.entities.User;

public class Program {

	public static void main(String[] args) {
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_19\\login.txt";
		File file = new File(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = br.readLine();
			
			Set<User> users = new HashSet<>();
			
			while(line != null) {
				String[] datas = line.split(" ");
				
				String name = datas[0];
				Instant moment = Instant.parse(datas[1]);
				
				User user = new User(name, moment);
				
				users.add(user);
				
				line = br.readLine();
			}
			
			System.out.println("Total users: " + users.size());
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
