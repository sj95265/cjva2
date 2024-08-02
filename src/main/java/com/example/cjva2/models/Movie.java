package com.example.cjva2.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "movies")
@Data
public class Movie {
    @Id
    private String id;
    private int movieId;  
    private String title;
    private String synopsis;
    private String smallPoster;
    private String largePoster;
    private double rentPrice;
    private double buyPrice;
    private boolean featured;
}
