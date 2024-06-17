package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			System.out.println("Room number:");
			int numberRoom = sc.nextInt();
			
			sc.nextLine();
			System.out.println("Check-in date (dd/MM/yyyy):");
			String date_string = sc.nextLine();
			
			LocalDate checkIn = LocalDate.parse(date_string, fmt);
			
			System.out.println("Check-out date (dd/MM/yyyy):");
			date_string = sc.nextLine();
			
			LocalDate checkOut = LocalDate.parse(date_string, fmt);
			
			Reservation reservation = new Reservation(numberRoom, checkIn, checkOut);
			
			System.out.println(reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.println("Check-in date (dd/MM/yyyy):");
			date_string = sc.nextLine();
			
			checkIn = LocalDate.parse(date_string, fmt);
			
			System.out.println("Check-out date (dd/MM/yyyy):");
			date_string = sc.nextLine();
			
			checkOut = LocalDate.parse(date_string, fmt);
			
			reservation.updateDates(checkIn, checkOut);
			
			System.out.println(reservation);
		} catch(DomainException e) {
			System.out.println(e.getMessage());
		} finally {
			sc.close();
		}
	}

}
