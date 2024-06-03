package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Rectangle;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter rectangle width and height");
		double width = sc.nextDouble();
		double height = sc.nextDouble();
		
		Rectangle rectangle = new Rectangle(width, height);
		
		System.out.println("AREA = " + rectangle.area());
		System.out.println("PERIMETER = " + rectangle.perimeter());
		System.out.println("DIAGONAL = " + rectangle.diagonal());
		
		sc.close();
	}

}
