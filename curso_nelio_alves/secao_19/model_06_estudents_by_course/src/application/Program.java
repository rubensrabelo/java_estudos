package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.entities.Course;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Course> courses = new ArrayList<>();
		Set<Integer> idStudents = new HashSet<>();
		
		for(int i = 0; i<3; i++) {
			System.out.println("Name course: ");
			String name = sc.nextLine();
			
			System.out.println("How many students per course? ");
			int qtd = sc.nextInt();
			
			courses.add(new Course(name));
			
			System.out.println("Ids students: ");
			for(int j = 0; j<qtd; j++) {
				Integer id = sc.nextInt();
				
				idStudents.add(id);
				
				courses.get(i).addIdStudents(id);
			}
			sc.nextLine();
		}
		
		System.out.println("Total students: " + idStudents.size());
		
		sc.close();
	}

}
