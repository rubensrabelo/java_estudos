package project_01_triangle.application;

import java.util.Locale;
import java.util.Scanner;

import project_01_triangle.entities.Triangle;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the measures of triangle X:");
		Double firstSide = sc.nextDouble();
		Double secondSide = sc.nextDouble();
		Double thirdSide = sc.nextDouble();
		
		Triangle x = new Triangle(firstSide, secondSide, thirdSide);
		Double areaX = x.area();
		
		System.out.println("Enter the measures of triangle Y:");
		firstSide = sc.nextDouble();
		secondSide = sc.nextDouble();
		thirdSide = sc.nextDouble();
		
		Triangle y = new Triangle(firstSide, secondSide, thirdSide);
		Double areaY = y.area();
		
		System.out.printf("Triangle X area: %.4f\n",  areaX);
		System.out.printf("Triangle Y area: %.4f\n",  areaY);
		
		if(areaX > areaY)
			System.out.println("Larger area: X");
		else
			System.out.println("Larger area: Y");
		
		sc.close();
	}

}
