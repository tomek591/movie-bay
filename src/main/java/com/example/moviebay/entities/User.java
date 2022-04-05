package com.example.moviebay.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> userMovies =  new ArrayList<>();

    public User() {
    }

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Movie> getUserMovies() {
        return userMovies;
    }

    public void setUserMovies(List<Movie> userMovies) {
        this.userMovies = userMovies;
    }

    public void addMovie(Movie movie) {
        userMovies.add(movie);
    }

    public void removeMovie(Movie movie) { userMovies.remove(movie); }
}
