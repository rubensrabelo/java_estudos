package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter account number: ");
		int number = sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter account holder:");
		String name = sc.nextLine();
		
		System.out.println("Is therena initial deposit (y/n)? ");
		char option = sc.next().toLowerCase().charAt(0);
		
		double balance = 0.0;
		Account account;
		if(option == 'y') {
			System.out.println("Enter initial deposit value:");
			balance  = sc.nextDouble();
			account = new Account(number, name, balance);
		} else {
			account = new Account(number, name);
		}
		
		System.out.println();
		System.out.println("Account data: ");
		System.out.println(account);
		
		System.out.println();
		System.out.println("Enter a deposit value: ");
		balance = sc.nextDouble();
		account.deposit(balance);
		System.out.println("Updated account data: ");
		System.out.println(account);
		
		System.out.println();
		System.out.println("Enter a withdraw value: ");
		balance = sc.nextDouble();
		account.withdraw(balance);
		System.out.println("Updated account data: ");
		System.out.println(account);
		
		sc.close();
	}
}
