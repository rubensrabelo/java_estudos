package ex_05_currency_converter.application;

import java.util.Locale;
import java.util.Scanner;

import ex_05_currency_converter.entities.CurrencyConverter;

public class Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is the dollar price?");
		double dollarPrice = sc.nextDouble();
		
		System.out.println("How many dollars will be bought?");
		double dollarBought = sc.nextDouble();
		
		CurrencyConverter cc = new CurrencyConverter(dollarPrice, dollarBought);
		
		System.out.println();
		System.out.printf("Amount to be paid in reais = %.2f\n", cc.amountToPay());
		
		sc.close();
	}
}
