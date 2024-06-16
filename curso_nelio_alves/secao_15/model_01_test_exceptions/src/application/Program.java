package application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			String[] names = sc.nextLine().split(" ");
			int index = sc.nextInt();
			
			System.out.println("The name is " + names[index]);
		} catch(InputMismatchException e) {
			System.out.println("Input error!");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Invalid position!");
		} finally {
			sc.close();
		}
	}

}
