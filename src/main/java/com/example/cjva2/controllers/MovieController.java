package com.example.cjva2.controllers;

import com.example.cjva2.models.Movie;
import com.example.cjva2.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        int newMovieId = movieService.getMaxMovieId() + 1;
        movie.setMovieId(newMovieId);
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMoviesByTitle(title));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Movie>> getFeaturedMovies() {
        return ResponseEntity.ok(movieService.getFeaturedMovies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        Optional<Movie> existingMovie = movieService.getMovieById(id);
        if (existingMovie.isPresent()) {
            Movie existing = existingMovie.get();
            movie.setId(id);
            movie.setMovieId(existing.getMovieId());
            return ResponseEntity.ok(movieService.updateMovie(movie));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable String id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            movieService.deleteMovie(movie.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
