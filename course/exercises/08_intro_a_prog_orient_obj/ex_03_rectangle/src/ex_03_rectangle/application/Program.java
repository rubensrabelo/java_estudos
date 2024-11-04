package ex_03_rectangle.application;

import java.util.Locale;
import java.util.Scanner;

import ex_03_rectangle.entities.Rectangle;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter rectangle width and height: ");
		double width = sc.nextDouble();
		double height = sc.nextDouble();
		
		Rectangle rec = new Rectangle(width, height);
		
		System.out.println();
		System.out.printf("AREA = %.2f\n", rec.area());
		System.out.printf("PERIMENTER = %.2f\n", rec.perimenter());
		System.out.printf("DIAGONAL = %.2f\n", rec.diagonal());
		
		sc.close();
	}

}
