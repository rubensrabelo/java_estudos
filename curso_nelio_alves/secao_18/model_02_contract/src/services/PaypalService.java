package services;

public class PaypalService implements OnlinePaymentService {
	
	public PaypalService(){
	}

	@Override
	public Double paymentFee(Double amount) {
		return amount + amount * 0.02;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		Double result = paymentFee(amount + amount * 0.01 * months);
		return result;
	}

}
