package br.com.alura.screenmatch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.screenmatch.models.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
}
