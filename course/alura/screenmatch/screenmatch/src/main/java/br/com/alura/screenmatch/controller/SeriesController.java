package br.com.alura.screenmatch.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.repositories.SeriesRepository;

@RestController
public class SeriesController {
	
	@Autowired
	private SeriesRepository repository;
	
	@GetMapping(value = "/series")
	public List<SeriesDTO> findAll() {
		return repository.findAll().stream()
				.map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeason(), s.getRatings(), 
						s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()))
				.collect(Collectors.toList()); 
	}
}
