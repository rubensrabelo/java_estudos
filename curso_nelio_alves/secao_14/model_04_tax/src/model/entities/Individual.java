package model.entities;

public class Individual extends TaxPayer {
	public Double healthExpenditures;
	
	public Individual() {
		super();
	}
	
	public Individual(String name, Double anualIncome, Double healthExpenditures) {
		super(name, anualIncome);
		this.healthExpenditures = healthExpenditures;
	}
	
	public Double getHealthExpenditures() {
		return healthExpenditures;
	}

	public void setHealthExpenditures(Double healthExpenditures) {
		this.healthExpenditures = healthExpenditures;
	}

	@Override
	public Double tax() {
		if(super.getAnualIncome() <= 20000)
			return super.getAnualIncome() * 0.15 - getHealthExpenditures() * 0.5;
		return super.getAnualIncome() * 0.25 - getHealthExpenditures() * 0.5;
	}
}
