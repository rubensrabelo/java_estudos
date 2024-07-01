package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.entities.User;

public class Program {

	public static void main(String[] args) {
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_19\\user.csv";
		File file = new File(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = br.readLine();
			
			line = br.readLine();
			
			List<User> users = new ArrayList<>();
			
			while(line != null) {
				String[] datas = line.split(",");
				
				String name = datas[0];
				String email = datas[1];
				String phoneNumber = datas[2];
				
				User user = new User(name, email, phoneNumber);
				
				users.add(user);
				
				line = br.readLine();
			}
			
			List<Map<String, String>> cookies = new ArrayList<>();
			
			for(User user : users) {
				Map<String, String> cookie = new LinkedHashMap<>();
				cookie.put("username", user.getName());
				cookie.put("email", user.getEmail());
				cookie.put("phoneNumber", user.getPhoneNumber());
				
				cookies.add(cookie);
			}
			
			System.out.println();
			System.out.println("SHOW ALL COOKIES:");
			
			for(Map<String, String> cookie : cookies) {
				System.out.println("------------------------------------");
				for(String key : cookie.keySet()) {					
					System.out.println(key + ": " + cookie.get(key));
				}
				System.out.println("------------------------------------");
				System.out.println();
			}
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
