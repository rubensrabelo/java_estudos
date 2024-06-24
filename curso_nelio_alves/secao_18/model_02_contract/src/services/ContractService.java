package services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		Double amount = contract.getTotalValue() / months;
		
		for(int i = 0; i < months; i++) {
			LocalDate nextDate = contract.getDate().plusMonths(i);
			
			amount = paymentService.interest(amount, i);
			
			Installment installment = new Installment(nextDate, amount);
			
			contract.addInstallments(installment);
		}
	}
}
