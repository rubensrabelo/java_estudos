package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.models.Category;

public record SeriesDTO(Long id, String title, Integer totalSeason, Double ratings, Category genre, String actors,
		String poster, String plot) {
}
