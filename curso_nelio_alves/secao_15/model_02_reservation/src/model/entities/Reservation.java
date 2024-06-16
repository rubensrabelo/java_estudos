package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.DomainException;

public class Reservation {
	public Integer roomNumber;
	public LocalDate checkIn;
	public LocalDate checkOut;
	
	public Reservation() {
	}

	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		if(!checkOut.isAfter(checkIn))
			throw new DomainException("Check-out date must be after check-in date");
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	public long duration() {
		return ChronoUnit.DAYS.between(checkOut, checkIn);
	}
	
	public void updateDates(LocalDate checkIn, LocalDate checkOut) {
		LocalDate now = LocalDate.now();
		
		if(checkIn.isBefore(now) || checkOut.isBefore(now))
			throw new DomainException("Reservation dates for update must be future dates");
		
		if(!checkOut.isAfter(checkIn))
			throw new DomainException("\"Check-out date must be after check-in date\"");
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		return "Reservation: Room " + getRoomNumber() + ", " +
				"Check-in: " + getCheckIn().format(fmt) + ", " + 
				"Check- out " + getCheckOut().format(fmt) + 
				", " + duration() + " nights";
	}
}
