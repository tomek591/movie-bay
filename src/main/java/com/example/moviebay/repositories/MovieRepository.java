package com.example.moviebay.repositories;

import com.example.moviebay.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
