package com.example.moviebay.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "movieId")

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @JsonProperty("movie_id")
    private Integer tmdbId;
    private String title;
    @JsonProperty("overview")
    private String description;
    @JsonProperty("release_date")
    private String releaseDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "userMovies", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Movie() {
    }

    public Movie(Integer tmdbId, String title, String description, String releaseDate) {

        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
