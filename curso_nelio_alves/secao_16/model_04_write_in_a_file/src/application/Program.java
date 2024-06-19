package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Program {

	public static void main(String[] args) {
		String[] datas = new String[] {"texto 1", "texto 2", "texto 3"};
		
		String path = "C:\\projetos_java\\curso_nelio_alves\\out.txt";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(String data : datas) {
				bw.write(data);
				bw.newLine();
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
