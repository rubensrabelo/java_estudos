package services;

import java.time.Duration;

import entities.CarRental;
import entities.Invoice;

public class RentalService {
	private Double pricePerhour;
	private Double pricePerDay;
	
	private TaxService taxService;
	
	public RentalService(Double pricePerhour, Double pricePerDay, TaxService taxService) {
		this.pricePerhour = pricePerhour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental car) {
		Double minutes = Long.valueOf(
				Duration.between(car.getStart(), car.getFinish()).toMinutes()
				).doubleValue();
		Double hour = minutes / 60;
		
		
		Double basicPayment;
		if(hour > 12)
			basicPayment = pricePerDay * Math.ceil(hour/24);
		else
			basicPayment = pricePerhour * Math.ceil(hour);
		
		Double tax = taxService.tax(basicPayment);
		
		car.setInvoice(new Invoice(basicPayment, tax));
	}
}
