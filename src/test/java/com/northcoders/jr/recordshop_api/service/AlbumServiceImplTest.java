package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.model.GENRE;
import com.northcoders.jr.recordshop_api.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class AlbumServiceImplTest {
    // TODO: ADD more tests for each method.

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImp;


    @Test
    void getAllAlbums() {
        // Arrange
        List<Album> expectedAlbumList = new ArrayList<>();
        expectedAlbumList.add(new Album(1L, "Album title 1", "Artist 1", GENRE.ROCK, 2001, 10));
        expectedAlbumList.add(new Album(2L, "Album title 2", "Artist 2", GENRE.POP, 2002, 20));
        expectedAlbumList.add(new Album(3L, "Album title 3", "Artist 3", GENRE.JAZZ, 2003, 30));

        when(mockAlbumRepository.findAll()).thenReturn(expectedAlbumList);

        // Act
        List<Album> result1 = albumServiceImp.getAllAlbums();

        // Assert
        assertThat(result1).hasSize(3);
        assertThat(result1).isEqualTo(expectedAlbumList);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).findAll();

    }

    @Test
    void getAlbumById() {
        List<Album> expectedAlbumList = new ArrayList<>();
        Album expectedAlbum1 = new Album(1L, "Album title 1", "Artist 1", GENRE.ROCK, 2001, 10);
        Album expectedAlbum2 = new Album(2L, "Album title 2", "Artist 2", GENRE.POP, 2002, 20);
        Album expectedAlbum3 = new Album(3L, "Album title 3", "Artist 3", GENRE.JAZZ, 2003, 30);

        Mockito.when(mockAlbumRepository.findById(1L)).thenReturn(Optional.of(expectedAlbum1));
        Mockito.when(mockAlbumRepository.findById(2L)).thenReturn(Optional.of(expectedAlbum2));
        Mockito.when(mockAlbumRepository.findById(3L)).thenReturn(Optional.of(expectedAlbum3));

        // Act
        Album result1 = albumServiceImp.getAlbumById(1L);
        Album result2 = albumServiceImp.getAlbumById(2L);
        Album result3 = albumServiceImp.getAlbumById(3L);

        // Assert
        assertThat(result1).isEqualTo(expectedAlbum1);
        assertThat(result2).isEqualTo(expectedAlbum2);
        assertThat(result3).isEqualTo(expectedAlbum3);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).findById(2L);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).findById(3L);

    }

    @Test
    void postAlbum() {
        // Arrange
        Album expectedAlbum1 = new Album(1L, "Album title 1", "Artist 1", GENRE.ROCK, 2001, 10);
        Album expectedAlbum2 = new Album("Title 2", "Artist 2");
        Album expectedAlbum3 = new Album(3L, "Album title 3", "Artist 3", GENRE.ROCK, 2003, 30);

        when(mockAlbumRepository.save(expectedAlbum1)).thenReturn(expectedAlbum1);
        when(mockAlbumRepository.save(expectedAlbum2)).thenReturn(expectedAlbum2);
        when(mockAlbumRepository.save(expectedAlbum3)).thenReturn(expectedAlbum3);

        // Act
        Album result1 = albumServiceImp.postAlbum(expectedAlbum1);
        Album result2 = albumServiceImp.postAlbum(expectedAlbum2);
        Album result3 = albumServiceImp.postAlbum(expectedAlbum3);

        // Assert
        System.out.println(result1);
        System.out.println(expectedAlbum1);
        System.out.println(result2);
        System.out.println(expectedAlbum2);
        System.out.println(result3);
        System.out.println(expectedAlbum3);
        assertThat(result1).isEqualTo(expectedAlbum1);
        assertThat(result2).isEqualTo(expectedAlbum2);
        assertThat(result3).isEqualTo(expectedAlbum3);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).save(expectedAlbum1);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).save(expectedAlbum2);
        Mockito.verify(mockAlbumRepository, Mockito.times(1)).save(expectedAlbum3);



    }
}
