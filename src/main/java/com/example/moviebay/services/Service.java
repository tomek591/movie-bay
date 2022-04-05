package com.example.moviebay.services;

import com.example.moviebay.entities.Movie;
import com.example.moviebay.entities.User;
import com.example.moviebay.repositories.MovieRepository;
import com.example.moviebay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> showAllUsersMovies(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        return user.getUserMovies();
    }

    public void addMovieToDB(Movie movie, Long userId, int id) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movieFromDb = movieRepository.findByTmdbId(id);
        assert user != null;
        if(movieRepository.existsByTmdbId(id)) {

            boolean isAlreadyAddToUser = false;
            for(int i = 0; i < user.getUserMovies().size(); i++) {
                if (Objects.equals(user.getUserMovies().get(i).getMovieId(), movieFromDb.getMovieId())) {
                    isAlreadyAddToUser = true;
                    break;
                }
            }

            if(!isAlreadyAddToUser) {
                user.addMovie(movieFromDb);
                userRepository.save(user);
            }
        } else {
            movie.setTmdbId(id);
            user.addMovie(movie);
            movieRepository.save(movie);
        }
    }

    public void removeMovieFromUser(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movieFromDb = movieRepository.findByTmdbId(Math.toIntExact(movieId));
        assert user != null;
        user.removeMovie(movieFromDb);
        userRepository.save(user);
    }
}
