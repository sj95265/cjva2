package com.example.cjva2.services;

import com.example.cjva2.models.Movie;
import com.example.cjva2.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;


@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getFeaturedMovies() {
        return movieRepository.findByFeatured(true);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    public int getMaxMovieId() {
        return movieRepository.findAll().stream()
                .max(Comparator.comparingInt(Movie::getMovieId))
                .map(Movie::getMovieId)
                .orElse(0); // Return 0 if there are no movies in the database
    }
}
