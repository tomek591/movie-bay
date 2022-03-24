package com.example.moviebay;

import com.example.moviebay.entities.Movie;
import com.example.moviebay.entities.User;
import com.example.moviebay.repositories.MovieRepository;
import com.example.moviebay.repositories.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MovieBayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(MovieBayApplication.class, args);

        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);

        MovieRepository movieRepository =
                configurableApplicationContext.getBean(MovieRepository.class);

        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        List<User> users = Arrays.asList(user1, user2, user3);

        Movie movie1 = new Movie(100000, "title1", "desc1", "date1");
        Movie movie2 = new Movie(100001, "title2", "desc2", "date2");
        Movie movie3 = new Movie(100002, "title3", "desc3", "date3");
        List<Movie> movies = Arrays.asList(movie1, movie2, movie3);

        movieRepository.saveAll(movies);

        user1.addMovie(movie1);
        user1.addMovie(movie3);
        user2.addMovie(movie2);
        user3.addMovie(movie1);

        userRepository.saveAll(users);
    }

}

