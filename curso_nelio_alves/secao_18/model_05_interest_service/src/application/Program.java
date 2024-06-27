package application;

import java.util.Scanner;

import services.BrazilInterestService;
import services.InterestService;
import services.UsaInterestService;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quantia: ");
		Double quantia = sc.nextDouble();
		
		System.out.println("Meses: ");
		Integer month = sc.nextInt();
		
		System.out.println();
		System.out.println("Pagamento apos 3 meses:" );
		
		InterestService brasilService = new BrazilInterestService(2.0);
		InterestService usaService = new UsaInterestService(1.0);
		
		System.out.println();
		System.out.println("Brazil Serive: " + String.format("%.2f", brasilService.payment(quantia, month)));
		
		System.out.println();
		System.out.println("Usa Serive: " + String.format("%.2f", usaService.payment(quantia, month)));
		
		sc.close();
	}

}
