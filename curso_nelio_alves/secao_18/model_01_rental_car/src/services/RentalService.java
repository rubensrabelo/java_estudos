package services;

import java.time.Duration;

import entities.CarRental;

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
	}
}
