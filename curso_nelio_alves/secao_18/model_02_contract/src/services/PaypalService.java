package services;

public class PaypalService implements OnlinePaymentService {
	
	public PaypalService(){
	}

	@Override
	public Double paymentFee(Double amount) {
		return amount * 0.02;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		return paymentFee(amount + 0.01 * months);
	}

}
