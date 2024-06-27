package services;

public class UsaInterestService implements InterestService {
	private double interestService;
	
	public UsaInterestService(Double interestService) {
		this.interestService = interestService;
	}
	
	@Override
	public Double getInterestRate() {
		return interestService;
	}
}
