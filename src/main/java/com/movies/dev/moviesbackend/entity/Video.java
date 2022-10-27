package com.movies.dev.moviesbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
}
