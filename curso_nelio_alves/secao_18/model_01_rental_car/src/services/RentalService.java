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
		Duration duration = Duration.between(car.getStart(), car.getFinish());
		long hour = duration.getSeconds() / 3600;
		
		Double basicPayment;
		if(hour > 12)
			basicPayment = pricePerhour * Math.ceil(hour/24);
		else
			basicPayment = pricePerDay * Math.ceil(hour);
		
		Double tax = taxService.tax(basicPayment);
		
		car.setInvoice(new Invoice(basicPayment, tax));
	}
}
