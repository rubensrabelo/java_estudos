package br.com.alura.screenmatch.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
	}
}
