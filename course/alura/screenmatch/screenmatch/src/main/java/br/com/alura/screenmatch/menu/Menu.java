package br.com.alura.screenmatch.menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.models.Episode;
import br.com.alura.screenmatch.models.EpisodeData;
import br.com.alura.screenmatch.models.SeasonsData;
import br.com.alura.screenmatch.models.SeriesData;
import br.com.alura.screenmatch.services.ConsumeApi;
import br.com.alura.screenmatch.services.DataConverter;

public class Menu {

	private final String ADDRESS = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=802bd4ce";

	private Scanner input = new Scanner(System.in);
	private ConsumeApi consume = new ConsumeApi();
	private DataConverter converter = new DataConverter();

	public void displayMenu() {
		System.out.println("Insert series name to search:");
		var seriesName = input.nextLine();
		
		var url = ADDRESS + seriesName.replace(" ", "+") + API_KEY;
		var json = consume.getData(url);
		SeriesData data = converter.getData(json, SeriesData.class);
		
		System.out.println(data);
		
		List<SeasonsData> seasons = new ArrayList<>();
        
        for(int i = 1; i<=data.totalSeasons(); i++) {
                json = consume.getData(ADDRESS + seriesName.replace(" ", "+") +"&season=" + i + API_KEY);  
                SeasonsData seasonData = converter.getData(json, SeasonsData.class);
                seasons.add(seasonData);

        }
        seasons.forEach(System.out::println);
        
        /*
        for(int i = 0; i < data.totalSeasons(); i++) {
        	List<EpisodeData> episodesSeason = seasons.get(i).episodes();
        	for(int j = 0; j < episodesSeason.size(); j++) {
        		System.out.println(episodesSeason.get(i).title());
        	}
        }
        */
        
        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));
        
        List<EpisodeData> episodeData = seasons.stream()
        									.flatMap(s -> s.episodes().stream())
        									.collect(Collectors.toList());
        
        episodeData.stream()
        			.filter(e -> !e.rating().equalsIgnoreCase("N/A"))
        			.sorted(Comparator.comparing(EpisodeData::rating).reversed())
        			.limit(5)
        			.forEach(System.out::println);
        
        List<Episode> episodes = seasons.stream()
        							.flatMap(s -> s.episodes().stream()
        									.map(d -> new Episode(d.number(), d)))
        							.collect(Collectors.toList());
        
        episodes.forEach(System.out::println);
        
        System.out.println("Enter an excerpt or title you are looking for: ");
        var partTitle = input.nextLine();
        
        Optional<Episode> episodeSeached = episodes.stream()
        		.filter(e -> e.getTitle().toUpperCase().contains(partTitle.toUpperCase()))
        		.findFirst();
        
        if(episodeSeached.isPresent()) 
        	System.out.println(episodeSeached);
        
        System.out.println("From what year do you want to watch the episodes? ");
        var year = input.nextInt();
        
        input.nextLine();
        
        LocalDate dateSearch = LocalDate.of(year, 1, 1);
        
        episodes.stream()
        	.filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(dateSearch))
        	.forEach(e -> System.out.println(
        			"Season: " + e.getSeason() +
        			", Title: " + e.getTitle() + 
        			", Release Date: " + e.getReleaseDate()
        			));
        
        Map<Integer, Double> ratingsForSeason = episodes.stream()
        								.filter(e -> e.getRating() > 0.0)
        								.collect(Collectors.groupingBy(Episode::getSeason, 
        										Collectors.averagingDouble(Episode::getRating)));
        
        System.out.println(ratingsForSeason);
        
        DoubleSummaryStatistics est = episodes.stream()
        		.filter(e -> e.getRating() > 0)
        		.collect(Collectors.summarizingDouble(Episode::getRating));
        
        System.out.println(est);
	}
}
