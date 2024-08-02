package com.example.cjva2.controllers;

import com.example.cjva2.models.TVShow;
import com.example.cjva2.services.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tvshows")
public class TVShowController {
    @Autowired
    private TVShowService tvShowService;

    @PostMapping
    public ResponseEntity<TVShow> createTVShow(@RequestBody TVShow tvShow) {
        int newTvShowId = tvShowService.getMaxTVShowId() + 1;
        tvShow.setTvShowId(newTvShowId);
        return ResponseEntity.ok(tvShowService.createTVShow(tvShow));
    }

    @GetMapping
    public ResponseEntity<List<TVShow>> getAllTVShows() {
        return ResponseEntity.ok(tvShowService.getAllTVShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TVShow> getTVShowById(@PathVariable String id) {
        Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
        return tvShow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<TVShow>> getTVShowsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(tvShowService.getTVShowsByTitle(title));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<TVShow>> getFeaturedTVShows() {
        return ResponseEntity.ok(tvShowService.getFeaturedTVShows());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TVShow> updateTVShow(@PathVariable String id, @RequestBody TVShow tvShow) {
        Optional<TVShow> existingShow = tvShowService.getTVShowById(id);
        if (existingShow.isPresent()) {
            TVShow existing = existingShow.get();
            tvShow.setId(id);
            tvShow.setTvShowId(existing.getTvShowId());
            return ResponseEntity.ok(tvShowService.updateTVShow(tvShow));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTVShowById(@PathVariable String id) {
        Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
        if (tvShow.isPresent()) {
            tvShowService.deleteTVShow(tvShow.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
