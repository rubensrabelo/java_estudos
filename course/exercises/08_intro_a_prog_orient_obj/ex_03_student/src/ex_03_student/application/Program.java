package ex_03_student.application;

import java.util.Locale;
import java.util.Scanner;

import ex_03_student.entities.Student;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input:");
		String name = sc.nextLine();
		
		Student std = new Student(name);
		
		for(int i = 0; i < 3; i++) {
			double value = sc.nextDouble();
			
			std.addGrade(value);
		}
		
		double valueGrade = std.average();
		
		if(valueGrade >= 60) {
			System.out.printf("FINAL GRADE = %.2f\n", valueGrade);
			System.out.println("PASS");
		} else {
			double diff = 60 - valueGrade;
			
			System.out.printf("FINAL GRADE = %.2f\n", valueGrade);
			System.out.println("FAILID");
			System.out.println("MISSING " + diff + " POINTS\n");
		}
		
		sc.close();
	}

}
