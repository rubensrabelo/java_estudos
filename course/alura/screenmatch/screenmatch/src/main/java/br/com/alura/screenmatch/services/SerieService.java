package br.com.alura.screenmatch.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.EpisodeDTO;
import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.models.Category;
import br.com.alura.screenmatch.models.Series;
import br.com.alura.screenmatch.repositories.SeriesRepository;

@Service
public class SerieService {
	
	@Autowired
	private SeriesRepository repository;
	
	public List<SeriesDTO> getAllSeries(){
		return converterData(repository.findAll());
	}
	
	public List<SeriesDTO> getTop5Series() {
		return converterData(repository.findTop5ByOrderByRatingsDesc());
	}
	
	public List<SeriesDTO> getRelease() {
		return converterData(repository.latestReleases());
	}
	
	public SeriesDTO getById(Long id) {
		Optional<Series> series = repository.findById(id);
		
		if(series.isPresent()) {
			Series s = series.get();
			return new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeason(), s.getRatings(), 
					s.getGenre(), s.getActors(), s.getPoster(), s.getPlot());
		}
		return null;
	}
	
	public List<EpisodeDTO> getTotalSeason(Long id) {
		Optional<Series> series = repository.findById(id);
		
		if(series.isPresent()) {
			Series s = series.get();
			return s.getEpisodes().stream()
					.map(e -> new EpisodeDTO(e.getSeason(), e.getTitle(), e.getNumber()))
					.collect(Collectors.toList());
		}
		
		return null;
	}
	
	public List<EpisodeDTO> getSeasonByNumber(Long id, Long number) {
		return repository.getEpisodesByNumber(id, number).stream()
				.map(e -> new EpisodeDTO(e.getSeason(), e.getTitle(), e.getNumber()))
				.collect(Collectors.toList());
	}
	
	public List<SeriesDTO> getSeriesByCategory(String nameCategory) {
		Category category = Category.fromString(nameCategory);
		return converterData(repository.findByCategory(category));
	}

	private List<SeriesDTO> converterData(List<Series> series) {
		return series.stream()
				.map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeason(), s.getRatings(), 
						s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()))
				.collect(Collectors.toList());
	}
}
