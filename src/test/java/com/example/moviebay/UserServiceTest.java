package com.example.moviebay;

import com.example.moviebay.entities.Movie;
import com.example.moviebay.entities.User;
import com.example.moviebay.repositories.MovieRepository;
import com.example.moviebay.repositories.UserRepository;
import com.example.moviebay.services.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private UserService userService;

    private Movie m1 = new Movie(12345, "title", "desc", "date");
    private Movie m2 = new Movie(12346, "title2", "desc2", "date2");
    private User u1 = new User(1L, "test1", "test1@email.com");
    private User u2 = new User(2L,"test2", "test2@email.com", new LinkedList<>(Arrays.asList(m1,m2)));


    @Test
    void showAllUsersMovies_simpleCase_success() {

        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(u2));
        List<Movie> result = userService.showAllUsersMovies(2L);
        Assertions.assertEquals(result, List.of(m1,m2));
    }

    @Test
    void showAllUsersMovies_emptyMoviesList_emptyList() {

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(u1));
        List<Movie> result = userService.showAllUsersMovies(1L);
        Assertions.assertEquals(result, List.of());
    }

    @Test
    void showAllUsersMovies_unknownUser_nullPointerExceptionConfirmed() {

        when(userRepository.findById(1L)).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, () -> userService.showAllUsersMovies(1L));
    }

    @Test
    void addMovieToDB_verifyCallsIfMovieExistsInDb_methodsCalled() {

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(u1));
        when(movieRepository.existsByTmdbId(12345)).thenReturn(true);
        when(movieRepository.findByTmdbId(12345)).thenReturn(m1);
        userService.addMovieToDB(m1, 1L, 12345L);
        verify(userRepository, times(1)).findById(1L);
        verify(movieRepository, times(1)).existsByTmdbId(12345);
        verify(movieRepository, times(1)).findByTmdbId(12345);
    }

    @Test
    void addMovieToDB_verifyCallsIfMovieNotExistsInDb_methodsCalled() {

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(u1));
        when(movieRepository.existsByTmdbId(12345)).thenReturn(false);
        userService.addMovieToDB(m1, 1L, 12345L);
        verify(userRepository, times(1)).findById(1L);
        verify(movieRepository, times(1)).existsByTmdbId(12345);
        verify(movieRepository, times(1)).save(m1);
    }

    @Test
    void addMovieToDB_unknownUser_nullPointerExceptionConfirmed() {

        when(userRepository.findById(1L)).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, () -> userService.addMovieToDB(m1,1L,1L));
    }

    @Test
    void assignIfMovieNotAssigned_verifyCallsIfMovieNotAssign_methodsCalled() {

        userService.assignIfMovieNotAssigned(u1,m1);
        verify(userRepository, times(1)).save(u1);
    }

    @Test
    void assignIfMovieNotAssigned_verifyCallsIfMovieAssign_methodsCalled() {

        userService.assignIfMovieNotAssigned(u2,m1);
        verify(userRepository, times(0)).save(u2);
    }

    @Test
    void removeMovieFromDB_verifyCalls_methodsCalled() {

        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(u2));
        when(movieRepository.findByTmdbId(12345)).thenReturn(m1);
        userService.removeMovieFromUser(2L, 12345L);
        verify(userRepository, times(1)).findById(2L);
        verify(movieRepository, times(1)).findByTmdbId(12345);
        verify(userRepository, times(1)).save(u2);
    }

    @Test
    void removeMovieFromDB_unknownUser_nullPointerExceptionConfirmed() {

        when(userRepository.findById(1L)).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, () -> userService.removeMovieFromUser(1L,1L));
    }





}
