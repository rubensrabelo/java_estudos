package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Student;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String name = sc.nextLine();
		
		Student student = new Student(name);
		
		Double grade = sc.nextDouble();
		student.addGrades(grade);
		grade = sc.nextDouble();
		student.addGrades(grade);
		grade = sc.nextDouble();
		student.addGrades(grade);
		
		System.out.println(student);
		
		sc.close();
	}

}
