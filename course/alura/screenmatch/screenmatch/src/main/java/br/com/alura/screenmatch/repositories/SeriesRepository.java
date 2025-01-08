package br.com.alura.screenmatch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.screenmatch.models.Category;
import br.com.alura.screenmatch.models.Episode;
import br.com.alura.screenmatch.models.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
	
	Optional<Series> findByTitleContainingIgnoreCase(String seriesName);

	List<Series> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, double ratings);

	List<Series> findTop5ByOrderByRatingsDesc();

	List<Series> findByCategory(Category category);
	
	@Query("SELECT s FROM Series s WHERE s.totalSeason <= :totalSeason AND s.ratings >= :ratings")
	List<Series> seriesPerSeasonAndRatings(int totalSeason, double ratings);
	
	@Query("SELECT e FROM Series s JOIN s.episode e WHERE e.title ILIKE %:partEpisode%")
	List<Episode> episodesByExcerpt(String partEpisode);
	
	@Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.ratings DESC LIMIT 5")
	List<Episode> topEpisodesBySeries(Series series);
	
	@Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series AND YEAR(e.releaseDate) >= :releaseYear")
	List<Episode> episodesBySeriesAndYear(Series series, int releaseYear);
	
	@Query(
			"SELECT s FROM Series s " +
			"JOIN e.episodes e " + 
			"GROUP BY s " +
			"ORDER BY MAX(e.releaseDate) DESC LIMIT 5"
			)
	List<Series> latestReleases();
	
	@Query("SELECT e FROM Series s JOIN s.episodes e WHERE s.id = :id AND e.season = :number")
	List<Episode> getEpisodesByNumber(Long id, Long number);
}
