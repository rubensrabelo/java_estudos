package services;

public interface InterestService {
	Double getInterestRate();
	
	default Double payment(Double amount, Integer month) {
		return amount * Math.pow((1 + getInterestRate()/100), month);
	}
}
