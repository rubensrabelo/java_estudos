package br.com.alura.screenmatch.models;

import java.time.DateTimeException;
import java.time.LocalDate;

import jakarta.persistence.ManyToOne;

public class Episode {
	
	private Integer season;
	private String title;
	private Integer number;
	private Double rating;
	private LocalDate releaseDate;
	
	@ManyToOne
	private Series serie;
	
	public Episode(Integer season, EpisodeData episodeData) {
		this.season = season;
		this.title = episodeData.title();
		this.number = episodeData.number();
		
		try {			
			this.rating = Double.valueOf(episodeData.rating());
			this.releaseDate = LocalDate.parse(episodeData.releaseDate());
		} catch (NumberFormatException e) {
			this.rating = 0.0;
		} catch(DateTimeException e) {
			this.releaseDate = null;
		}
	}
	
	public Integer getSeason() {
		return season;
	}
	
	public void setSeason(Integer season) {
		this.season = season;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Double getRating() {
		return rating;
	}
	
	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	

	public Series getSerie() {
		return serie;
	}

	public void setSerie(Series serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "Episode [season=" + season + ", title=" + title + ", number=" + number + ", rating=" + rating
				+ ", releaseDate=" + releaseDate + "]";
	}
}
