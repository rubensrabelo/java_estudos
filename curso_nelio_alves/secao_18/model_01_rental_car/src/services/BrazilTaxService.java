package services;

public class BrazilTaxService implements TaxService {
	@Override
	public Double tax(Double amount) {
		if(amount > 100)
			return 0.15;
		return 0.20;
	}

}
