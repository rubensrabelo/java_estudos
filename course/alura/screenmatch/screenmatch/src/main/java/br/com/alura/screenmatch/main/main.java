package br.com.alura.screenmatch.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.models.Category;
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

		while (option != 0) {
			var menu = """
					1 - Search series
					2 - Search episodes
					3 - List searched series
					4 - Search series by title
					5 - Search series by actor
					6 - Top 5 Series
					7 - Search series by category
					8 - Filtrar séries
					9 - Search episodes by segment
					10 - Top 5 episodes per series
					11 - Search for episodes from a date

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
			case 4:
				searchSeriesByTitle();
				break;
			case 5:
				searchSeriesByActor();
				break;
			case 6:
				searchTop5Series();
				break;
			case 7:
				searchSeriesByCategory();
				break;
			case 8:
				filterSeriesBySeasonEvaluation();
				break;
			case 9:
				searchEpisodioPorExcerpt();
				break;
			case 10:
				topEpisodesBySeries();
				break;
			case 11:
				searchEpisodesAfterADate();
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
				.filter(s -> s.getTitle().toLowerCase().contains(nameSeries.toLowerCase())).findFirst();

		if (serie.isPresent()) {
			// SeriesData seriesData = getDataSerie();
			var seriesSearched = serie.get();
			List<SeasonsData> seasons = new ArrayList<>();

			for (int i = 0; i < seriesSearched.getTotalSeason(); i++) {
				var json = consume
						.getData(ADDRESS + seriesSearched.getTitle().replace(" ", "+" + "&season=" + i + API_KEY));
				SeasonsData seasonsData = converter.getData(json, SeasonsData.class);
				seasons.add(seasonsData);
			}
			seasons.forEach(System.out::println);

			List<Episode> episodes = seasons.stream()
					.flatMap(d -> d.episodes().stream().map(e -> new Episode(d.number(), e)))
					.collect(Collectors.toList());

			seriesSearched.setEpisodes(episodes);
			repository.save(seriesSearched);
		} else {
			System.out.println("Serie not found.");
		}

	}

	private void listSeriesSearched() {
		series = repository.findAll();
		series.stream().sorted(Comparator.comparing(Series::getGenre)).forEach(System.out::println);
		;
	}

	private void searchSeriesByTitle() {
		System.out.println("Choose a series by name:");
		var seriesName = input.nextLine();
		Optional<Series> searchedSeries = repository.findByTitleContainingIgnoreCase(seriesName);

		if (searchedSeries.isPresent()) {
			System.out.println("Series data: " + searchedSeries.get());
		} else {
			System.out.println("Series not found.");
		}
	}

	private void searchSeriesByActor() {
		System.out.println("What is the search name?");
		var actorName = input.nextLine();

		System.out.println("Assessments starting at what value?");
		var ratings = input.nextDouble();

		List<Series> searchedSeries = repository.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(actorName,
				ratings);

		System.out.println("Series " + actorName + " worked on:");
		searchedSeries.forEach(s -> System.out.println(s.getTitle() + ", Rating: " + s.getRatings()));
	}

	private void searchTop5Series() {
		List<Series> topSeries = repository.findTop5ByOrderByRatings();
		topSeries.forEach(s -> System.out.println(s.getTitle() + ", Ratings: " + s.getRatings()));
	}

	private void searchSeriesByCategory() {
		System.out.println("What category/genre do you want to search for series?");
		var categoryName = input.nextLine();
		Category category = Category.fromString(categoryName);
		List<Series> seriesByCategory = repository.findByCategory(category);
		System.out.println("Series by category: " + categoryName);
		seriesByCategory.forEach(System.out::println);
	}

	private void filterSeriesBySeasonEvaluation() {
		System.out.println("Filter series up to how many seasons?");
		var totalSeason = input.nextInt();
		input.nextLine();
		System.out.println("With an assessment based on what value?");
		var ratings = input.nextDouble();
		input.nextLine();
		List<Series> filteredSeries = repository.seriesPerSeasonEAValiacao(totalSeason, ratings);
		System.out.println("Filtered series:");
		filteredSeries.forEach(s -> System.out.println(s.getTitle() + " - Ratings: " + s.getRatings()));
	}

	private void searchEpisodioPorExcerpt() {
		System.out.println("What is the name of the episode to search for?");
		var partEpisode = input.nextLine();
		List<Episode> searchedEpisode = repository.episodesByExcerpt(partEpisode);
		searchedEpisode.forEach(e -> 
							System.out.printf("Series: %s Season %s - Episode %s - Ratings %s\n",
									e.getSerie().getTitle(), e.getSeason(),
									e.getNumber(), e.getTitle(), e.getRating()));
	}
	
	private void topEpisodesBySeries() {
		searchSeriesByTitle();
	}
}
