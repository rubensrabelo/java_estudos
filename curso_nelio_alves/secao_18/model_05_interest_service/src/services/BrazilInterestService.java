package services;

public class BrazilInterestService implements InterestService {
	private double interestService;
	
	public BrazilInterestService(Double interestService) {
		this.interestService = interestService;
	}
	
	@Override
	public Double getInterestRate() {
		// TODO Auto-generated method stub
		return interestService;
	}

}
