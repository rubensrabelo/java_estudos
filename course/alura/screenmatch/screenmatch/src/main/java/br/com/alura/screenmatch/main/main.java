package br.com.alura.screenmatch.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.models.Episode;
import br.com.alura.screenmatch.models.SeasonsData;
import br.com.alura.screenmatch.models.Series;
import br.com.alura.screenmatch.models.SeriesData;
import br.com.alura.screenmatch.repositories.SeriesRepository;
import br.com.alura.screenmatch.services.ConsumeApi;
import br.com.alura.screenmatch.services.DataConverter;

public class main {

	private final String ADDRESS = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=";
	
	private Scanner input = new Scanner(System.in);
	private ConsumeApi consume = new ConsumeApi();
	private DataConverter converter = new DataConverter();

	// private List<SeriesData> seriesData = new ArrayList<>();
	
	private SeriesRepository repository;
	
	private List<Series> series = new ArrayList<>();
	
	public main(SeriesRepository repository) {
		this.repository = repository;
	}

	public void displayMenu() {
		var option = -1;
		
		while(option != 0) {
			var menu = """
					1 - Search series
					2 - Search episodes
					3 - List searched series
					
					0 - Quit
					""";
			
			System.out.println(menu);
			option = input.nextInt();
			input.nextLine();
			
			switch (option) {
            case 1:
                searchSerieWeb();
                break;
            case 2:
                searchEpisodeBySerie();
                break;
            case 3:
                listSeriesSearched();
                break;
            case 0:
                System.out.println("Quit...");
                break;
            default:
                System.out.println("Invalid option");
        }
		}
	}

	private void searchSerieWeb() {
		SeriesData datas = getDataSerie();
		Series serie = new Series(datas);
		// seriesData.add(datas);
		repository.save(serie);
		System.out.println(datas);
	}

	private SeriesData getDataSerie() {
		System.out.println("Insert series name searched");
		var nameSerie = input.nextLine();
		var json = consume.getData(ADDRESS + nameSerie.replace(" ", "+") + API_KEY);
		SeriesData datas = converter.getData(json, SeriesData.class);
		return datas;
	}

	private void searchEpisodeBySerie() {
		listSeriesSearched();
		System.out.println("Choose series's name: ");
		var nameSeries = input.nextLine();
		
		Optional<Series> serie = series.stream()
					.filter(s -> s.getTitle().toLowerCase().contains(nameSeries.toLowerCase()))
					.findFirst();
		
		if(serie.isPresent()) {
			// SeriesData seriesData = getDataSerie();
			var seriesSearched = serie.get();
			List<SeasonsData> seasons = new ArrayList<>();
			
			for(int i = 0; i< seriesSearched.getTotalSeason(); i++) {
				var json = consume.getData(ADDRESS + seriesSearched.getTitle().replace(" ", "+" + "&season=" + i + API_KEY));
				SeasonsData seasonsData = converter.getData(json, SeasonsData.class);
				seasons.add(seasonsData);
			}
			seasons.forEach(System.out::println);
			
			List<Episode> episodes = seasons.stream()
					.flatMap(d -> d.episodes().stream()
							.map(e -> new Episode(d.number(), e)))
					.collect(Collectors.toList());
			
			seriesSearched.setEpisodes(episodes);
			repository.save(seriesSearched);
		} else {
			System.out.println("Serie not found.");
		}
		
	}

	private void listSeriesSearched() {
		series = repository.findAll();
		series.stream()
			.sorted(Comparator.comparing(Series::getGenre))
			.forEach(System.out::println);;
	}
}
