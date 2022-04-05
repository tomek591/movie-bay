package com.example.moviebay.controllers;

import com.example.moviebay.entities.Movie;
import com.example.moviebay.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@RestController
public class Controller {

    @Autowired
    private Service service;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}/movies")
    public List<Movie> getAllUsersMovies(@PathVariable Long userId){
        return service.showAllUsersMovies(userId);
    }

    @PostMapping("/{userId}/add-movie/{movieId}")
    public void addMovieFromAPI(@PathVariable Long userId,@PathVariable Long movieId){
        Movie movie = webClientBuilder.build()
                .get()
                .uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=yourapikey")
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
        assert movie != null;
        service.addMovieToDB(movie, userId, Math.toIntExact(movieId));
    }

    @DeleteMapping("/{userId}/delete-movie/{movieId}")
    public void deleteMovieFromUser(@PathVariable Long userId, @PathVariable Long movieId) {
        service.removeMovieFromUser(userId, movieId);
    }
}
