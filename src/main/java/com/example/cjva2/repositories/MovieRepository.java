package com.example.cjva2.repositories;

import com.example.cjva2.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByFeatured(boolean featured);
}
