package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.model.GENRE;
import com.northcoders.jr.recordshop_api.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class AlbumServiceImplTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImp;


    @Test
    void getAllAlbums() {
        // Arrange
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Album title 1", "Artist 1", GENRE.ROCK, 2001, 10));
        albums.add(new Album(2L, "Album title 2", "Artist 2", GENRE.POP, 2002, 20));
        albums.add(new Album(3L, "Album title 3", "Artist 3", GENRE.JAZZ, 2003, 30));

        when(mockAlbumRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> result1 = albumServiceImp.getAllAlbums();

        // Assert
        assertThat(result1).hasSize(3);
        assertThat(result1).isEqualTo(albums);
    }

    @Test
    void postAlbum() {
    }
}
