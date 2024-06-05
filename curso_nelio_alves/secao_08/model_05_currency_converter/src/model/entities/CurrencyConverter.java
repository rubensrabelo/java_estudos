package model.entities;

public class CurrencyConverter {
	public static final double IOF = 0.06;
	
	private double dollarPrice;
	private double boughtDollar;
	
	public CurrencyConverter() {
	}

	public CurrencyConverter(double boughtDollar, double dollarPrice) {
		this.boughtDollar = boughtDollar;
		this.dollarPrice = dollarPrice;
	}

	public double getBoughtDollar() {
		return boughtDollar;
	}

	public void setBoughtDollar(double boughtDollar) {
		this.boughtDollar = boughtDollar;
	}
	
	public double getDollarPrice() {
		return this.dollarPrice;
	}

	public void setDollarPrice(double dollarPrice) {
		this.dollarPrice = dollarPrice;
	}
	
	public double paymentInReais() {
		double result = getDollarPrice() * getBoughtDollar();
		result += result * IOF;
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Amount to be paid in reais = " + String.format("%.2f", paymentInReais());
	}
}
