package com.northcoders.jr.recordshop_api.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor // Hibernate requires this
@AllArgsConstructor // Full-args constructor
@RequiredArgsConstructor // Required-args constructor for @NonNull fields
public class Album {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // TODO: FIX id defaulting to null instead of generating - check on postman first
    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(nullable = false)
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column
    private GENRE genre = GENRE.UNKNOWN;

    @Column
    private Integer releaseYear = 0;

    @Column
    private Integer stockCount = 0;
}
