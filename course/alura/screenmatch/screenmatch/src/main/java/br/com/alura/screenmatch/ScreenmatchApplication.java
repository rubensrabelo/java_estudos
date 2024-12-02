package br.com.alura.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.models.EpisodeData;
import br.com.alura.screenmatch.models.SeasonsData;
import br.com.alura.screenmatch.models.SeriesData;
import br.com.alura.screenmatch.services.ConsumeApi;
import br.com.alura.screenmatch.services.DataConverter;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String key = "";
		
		var consumeApi = new ConsumeApi();
		String url = "https://www.omdbapi.com/?t=gilmore+girls&apikey=" + key;
		var json = consumeApi.getData(url);
		
		DataConverter converter = new DataConverter();
		SeriesData data = converter.getData(json, SeriesData.class);
		
		System.out.println(data);
		
		url = "https://omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=" + key;
		
		json = consumeApi.getData(url);

		EpisodeData episodeData = converter.getData(json, EpisodeData.class);
		
		System.out.println(episodeData);
		
		List<SeasonsData> seasons = new ArrayList<>();
		
		for(int i = 0; i <= data.totalSeasons(); i++) {
			url = "https://omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=" + key;
			json = consumeApi.getData(url);
			
			SeasonsData seasonData = converter.getData(json, SeasonsData.class);
			seasons.add(seasonData);
		}
		
		seasons.forEach(System.out::println);
	}

}
