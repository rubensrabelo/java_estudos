package application;

import java.io.File;

public class Program {

	public static void main(String[] args) {
		String path_str = "C:\\projetos_java\\curso_nelio_alves";
		
		File path = new File(path_str);
		
		File[] folders = path.listFiles(File::isDirectory);
		
		System.out.println("FOLDERS:");
		System.out.println();
		
		for( File folder : folders)
			System.out.println(folder);
		
		System.out.println();
		System.out.println("FILES:");
		System.out.println();
		
		File[] files = path.listFiles(File::isFile);
		
		for( File file : files)
			System.out.println(file);
		
		System.out.println();
		boolean sucess = new File(path + "\\teste").mkdir();
		System.out.println("Directory created successfully: " + sucess);
	}
}
