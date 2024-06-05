package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.CurrencyConverter;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is the dollar price? ");
		double dollarPrice = sc.nextDouble();
		System.out.println("How many dollars will be brought?");
		double dollarBrought = sc.nextDouble();
		
		CurrencyConverter cc = new CurrencyConverter(dollarPrice, dollarBrought);
		
		System.out.println(cc);
		
		sc.close();
	}

}
