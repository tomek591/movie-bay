package com.example.moviebay.controllers;

import com.example.moviebay.entities.Movie;
import com.example.moviebay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}/movies")
    public List<Movie> getAllUsersMovies(@PathVariable Long userId){

        return userService.showAllUsersMovies(userId);
    }

    @PostMapping("/{userId}/add-movie/{movieId}")
    public void addMovieFromAPI(@PathVariable Long userId,@PathVariable Long movieId){

        Movie movie = webClientBuilder.build()
                .get()
                .uri("https://api.themoviedb.org/3/movie/" + movieId + "?yourkey")
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
        assert movie != null;
        userService.addMovieToDB(movie, userId, movieId);
    }

    @DeleteMapping("/{userId}/delete-movie/{movieId}")
    public void deleteMovieFromUser(@PathVariable Long userId, @PathVariable Long movieId) {

        userService.removeMovieFromUser(userId, movieId);
    }
}
