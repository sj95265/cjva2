package com.example.cjva2.repositories;

import com.example.cjva2.models.TVShow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TVShowRepository extends MongoRepository<TVShow, String> {
    List<TVShow> findByTitleContainingIgnoreCase(String title);
    List<TVShow> findByFeatured(boolean featured);
}
