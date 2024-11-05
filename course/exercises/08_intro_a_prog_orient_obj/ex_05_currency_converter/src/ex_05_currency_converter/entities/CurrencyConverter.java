package ex_05_currency_converter.entities;

public class CurrencyConverter {
	
	private static final double IOF = 0.06;
	
	private Double dollarPrice;
	private Double dollarBought;
	
	public CurrencyConverter() {
	}

	public CurrencyConverter(Double dollarPrice, Double dollarBought) {
		this.dollarPrice = dollarPrice;
		this.dollarBought = dollarBought;
	}

	public Double getDollarPrice() {
		return dollarPrice;
	}

	public void setDollarPrice(Double dollarPrice) {
		this.dollarPrice = dollarPrice;
	}

	public Double getDollarBought() {
		return dollarBought;
	}

	public void setDollarBought(Double dollarBought) {
		this.dollarBought = dollarBought;
	}
	
	public Double getIOF() {
		return IOF;
	}
	
	public double amountToPay() {
		double amountWithoutIOF = dollarPrice * dollarBought;
		
		amountWithoutIOF += amountWithoutIOF * IOF;
		
		return amountWithoutIOF;
	}
}
