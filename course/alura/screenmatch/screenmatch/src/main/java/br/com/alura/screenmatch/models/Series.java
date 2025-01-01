package br.com.alura.screenmatch.models;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_series")
public class Series {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Integer totalSeason;
	private Double ratings;
	
	@Enumerated(EnumType.STRING)
	private Category genre;
	private String actors;
	private String poster;
	private String plot;
	
	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Episode> episodes = new ArrayList<>();
	
	public Series() {
	}
	
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

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		episodes.forEach(e -> e.setSerie(this));
		this.episodes = episodes;
	}

	@Override
	public String toString() {
		return
                "genre=" + genre +
                        ", title='" + title + '\'' +
                        ", total seasons=" + totalSeason +
                        ", ratings=" + ratings +
                        ", actors='" + actors + '\'' +
                        ", poster='" + poster + '\'' +
                        ", episodes='" + episodes + '\'';
	}
}
