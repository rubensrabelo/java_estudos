package application;

import java.util.Scanner;

import services.PrintService;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many values?");
		int qtd = sc.nextInt();
		
		PrintService print = new PrintService();
		
		for(int i=0; i<qtd; i++) {
			int number = sc.nextInt();
			
			print.addValue(number);
		}
		
		print.print();
		
		System.out.println("First: " + print.first());
		
		sc.close();
	}

}
