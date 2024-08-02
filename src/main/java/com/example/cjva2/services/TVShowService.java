package com.example.cjva2.services;

import com.example.cjva2.models.TVShow;
import com.example.cjva2.repositories.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TVShowService {
    @Autowired
    private TVShowRepository tvShowRepository;

    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }

    public Optional<TVShow> getTVShowById(String id) {
        return tvShowRepository.findById(id);
    }

    public List<TVShow> getTVShowsByTitle(String title) {
        return tvShowRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<TVShow> getFeaturedTVShows() {
        return tvShowRepository.findByFeatured(true);
    }

    public TVShow createTVShow(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    public TVShow updateTVShow(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    public void deleteTVShow(String id) {
        tvShowRepository.deleteById(id);
    }

    public int getMaxTVShowId() {
        return tvShowRepository.findAll().stream()
                .max(Comparator.comparingInt(TVShow::getTvShowId))
                .map(TVShow::getTvShowId)
                .orElse(0); // Return 0 if there are no movies in the database
    }
}
