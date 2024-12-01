package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.services.ConsumeApi;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumeApi = new ConsumeApi();
		String url = "https://www.omdbapi.com/?t=gilmore+girls&apikey=";
		var json = consumeApi.getData(url);
		System.out.println(json);
	}

}
