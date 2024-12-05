package com.northcoders.jr.recordshop_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column
    private GENRE genre;

    @Column
    private int releaseYear = 0;

    @Column
    private int stockCount = 0;

}
