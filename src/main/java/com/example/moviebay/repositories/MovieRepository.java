package com.example.moviebay.repositories;

import com.example.moviebay.entities.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    boolean existsByTmdbId(int tmdbId);

    Movie findByTmdbId(int tmdbId);

}
