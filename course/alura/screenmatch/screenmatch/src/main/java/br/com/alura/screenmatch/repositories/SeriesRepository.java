package br.com.alura.screenmatch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.screenmatch.models.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
	
	Optional<Series> findByTitleContainingIgnoreCase(String seriesName);

	List<Series> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, double ratings);

	List<Series> findTop5ByOrderByRatings();
}
