package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Program {

	public static void main(String[] args) {
		String path = "C:\\projetos_java\\curso_nelio_alves\\secao_19\\votacao.csv";
		File file = new File(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String line = br.readLine();
			
			line = br.readLine();
			
			List<Map<String, Integer>> votesByPersonList = new ArrayList<>();
			
			while(line != null) {
				String[] values = line.split(",");
				
				String name = values[0];
				Integer votes = Integer.parseInt(values[1]);
				
				Map<String, Integer> votesByPerson = new LinkedHashMap<>();
				
				votesByPerson.put(name, votes);
				
				if(votesByPersonList.isEmpty()) {
					votesByPersonList.add(votesByPerson);
				} else {
					
					for(Map<String, Integer> verify : votesByPersonList) {
						if(votesByPerson.equals(verify)) {
							// Encontrei o valor, vou ter que somar o valor e dá um jeito de
							// colocar valores caso seja falso
							Integer currentVotes = verify.get(name);
							verify.put(name, currentVotes + votes);
						}
					}
				}
				
				line = br.readLine();
			}
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
