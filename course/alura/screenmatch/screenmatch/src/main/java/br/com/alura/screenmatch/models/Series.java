package br.com.alura.screenmatch.models;

import java.util.OptionalDouble;

public class Series {
	
	private String title;
	private Integer totalSeason;
	private Double ratings;
	private Category genre;
	private String actors;
	private String poster;
	private String plot;
	
	public Series(SeriesData seriesData) {
		this.title = seriesData.title();
		this.totalSeason = seriesData.totalSeasons();
		this.ratings = OptionalDouble.of(Double.valueOf(seriesData.rating())).orElse(0);
		this.genre = Category.fromString(seriesData.genre());
		this.actors = seriesData.actors();
		this.poster = seriesData.plot();
		this.plot = seriesData.plot();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalSeason() {
		return totalSeason;
	}

	public void setTotalSeason(Integer totalSeason) {
		this.totalSeason = totalSeason;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public Category getGenre() {
		return genre;
	}

	public void setGenre(Category genre) {
		this.genre = genre;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	@Override
	public String toString() {
		return "Series [title=" + title + ", totalSeason=" + totalSeason + ", ratings=" + ratings + ", genre=" + genre
				+ ", actors=" + actors + ", poster=" + poster + ", plot=" + plot + "]";
	}
}
