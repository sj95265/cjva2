package com.example.cjva2.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "tvShows")
@Data
public class TVShow {
    @Id
    private String id;
    private int tvShowId;  
    private String title;
    private String synopsis;
    private String smallPoster;
    private String largePoster;
    private double rentPrice;
    private double buyPrice;
    private boolean featured;
}
