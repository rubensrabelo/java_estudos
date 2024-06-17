package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter account data");
			System.out.println("Number: ");
			int number = sc.nextInt();
			
			sc.nextLine();
			System.out.println("Holder: ");
			String holder = sc.nextLine();
			
			System.out.println("Initial balance:");
			Double balance = sc.nextDouble();
			
			System.out.println("Withdraw limit:");
			Double withdrawLimit = sc.nextDouble();
			
			Account account = new Account(number, holder, balance, withdrawLimit);
			
			System.out.println();
			System.out.println(account);
			
			System.out.println();
			System.out.println("Enter amount for withdraw:");
			balance = sc.nextDouble();
			
			account.withdraw(balance);
			
			System.out.println();
			System.out.println(account);
		} catch(DomainException e) {
			System.out.println(e.getMessage());
		} finally {
			sc.close();
		}
	}

}
