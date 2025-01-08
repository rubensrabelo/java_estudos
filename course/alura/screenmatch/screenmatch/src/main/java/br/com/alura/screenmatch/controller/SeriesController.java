package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.EpisodeDTO;
import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.services.SerieService;

@RestController
@RequestMapping("/series")
public class SeriesController {
	
	@Autowired
	private SerieService service;
	
	@GetMapping
	public List<SeriesDTO> getSeries() {
		return service.getAllSeries(); 
	}
	
	@GetMapping
	public List<SeriesDTO> getTop5Series() {
		return service.getTop5Series();
	}
	
	@GetMapping
	public List<SeriesDTO> getRelease() {
		return service.getRelease();
	}
	
	@GetMapping("/{id}")
	public SeriesDTO getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/{id}")
	public List<EpisodeDTO> getTotalSeason(@PathVariable Long id) {
		return service.getTotalSeason(id);
	}
	
	@GetMapping("/{id}/{number}")
	public List<EpisodeDTO> getSeasonByNumber(@PathVariable Long id, @PathVariable Long number) {
		return service.getSeasonByNumber(id, number);
	}
	
	@GetMapping("/{nameCategory}")
	public List<SeriesDTO> getSeriesByCategory(@PathVariable String nameGategory) {
		return service.getSeriesByCategory(nameGategory);
	}
}
