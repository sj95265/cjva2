package com.example.cjva2.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
public class Customer {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
}
