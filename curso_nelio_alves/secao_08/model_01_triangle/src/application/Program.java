package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Triangle;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the measures of triangle X");
		double side01 = sc.nextDouble();
		double side02 = sc.nextDouble();
		double side03 = sc.nextDouble();
		
		Triangle x = new Triangle(side01, side02, side03);
		
		System.out.println("Enter the measures of triangle y");
		side01 = sc.nextDouble();
		side02 = sc.nextDouble();
		side03 = sc.nextDouble();
		
		Triangle y = new Triangle(side01, side02, side03);
		
		double areaX = x.area();
		double areaY = y.area();
		
		System.out.println("Triangle X area: " + areaX);
		System.out.println("Triangle Y area: " + areaY);
		
		if(areaX > areaY) {
			System.out.println("Larger area: X");
		} else {
			System.out.println("Larger area: Y");
		}
		
		sc.close();
	}
}
