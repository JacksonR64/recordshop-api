package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.model.GENRE;
import com.northcoders.jr.recordshop_api.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlbumServiceImplTest {
    // TODO: ADD more tests for each method. including bad paths and edge cases
    // TODO: Add display names

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImp;

    @Autowired
    private Environment environment; //TODO: Remove after debug

    @Test
    void getAllAlbums() {
        // Arrange
        List<Album> expectedAlbumList = new ArrayList<>();
        expectedAlbumList.add(new Album(1L, "Album title 1", "Artist name 1", GENRE.ROCK, 2001, 10));
        expectedAlbumList.add(new Album(2L, "Album title 2", "Artist name 2", GENRE.POP, 2002, 20));
        expectedAlbumList.add(new Album(3L, "Album title 3", "Artist name 3", GENRE.JAZZ, 2003, 30));

        when(mockAlbumRepository.findAll()).thenReturn(expectedAlbumList);

        // Act
        List<Album> result1 = albumServiceImp.getAllAlbums();

        // Assert
        assertThat(result1).isEqualTo(expectedAlbumList);
        verify(mockAlbumRepository, times(1)).findAll();

    }

    @Test
    void getAlbumById() {
        List<Album> expectedAlbumList = new ArrayList<>();
        Album expectedAlbum1 = new Album(1L, "Album title 1", "Artist name 1", GENRE.ROCK, 2001, 10);
        Album expectedAlbum2 = new Album(2L, "Album title 2", "Artist name 2", GENRE.POP, 2002, 20);
        Album expectedAlbum3 = new Album(3L, "Album title 3", "Artist name 3", GENRE.JAZZ, 2003, 30);

        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.of(expectedAlbum1));
        when(mockAlbumRepository.findById(2L)).thenReturn(Optional.of(expectedAlbum2));
        when(mockAlbumRepository.findById(3L)).thenReturn(Optional.of(expectedAlbum3));

        // Act
        Album result1 = albumServiceImp.getAlbumById(1L);
        Album result2 = albumServiceImp.getAlbumById(2L);
        Album result3 = albumServiceImp.getAlbumById(3L);

        // Assert
        assertThat(result1).isEqualTo(expectedAlbum1);
        assertThat(result2).isEqualTo(expectedAlbum2);
        assertThat(result3).isEqualTo(expectedAlbum3);
        verify(mockAlbumRepository, times(1)).findById(1L);
        verify(mockAlbumRepository, times(1)).findById(2L);
        verify(mockAlbumRepository, times(1)).findById(3L);

    }

    @Test
    void postAlbum() {
        // Arrange
        Album expectedAlbum1 = new Album(1L, "Album title 1", "Artist name 1", GENRE.ROCK, 2001, 10);
        Album expectedAlbum2 = new Album("Album title 2", "Artist name 2");
        Album expectedAlbum3 = new Album(3L, "Album title 3", "Artist name 3", GENRE.ROCK, 2003, 30);

        when(mockAlbumRepository.save(expectedAlbum1)).thenReturn(expectedAlbum1);
        when(mockAlbumRepository.save(expectedAlbum2)).thenReturn(expectedAlbum2);
        when(mockAlbumRepository.save(expectedAlbum3)).thenReturn(expectedAlbum3);

        // Act
        Album result1 = albumServiceImp.postAlbum(expectedAlbum1);
        Album result2 = albumServiceImp.postAlbum(expectedAlbum2);
        Album result3 = albumServiceImp.postAlbum(expectedAlbum3);

        // Assert
        assertThat(result1).isEqualTo(expectedAlbum1);
        assertThat(result2).isEqualTo(expectedAlbum2);
        assertThat(result3).isEqualTo(expectedAlbum3);
        verify(mockAlbumRepository, times(1)).save(expectedAlbum1);
        verify(mockAlbumRepository, times(1)).save(expectedAlbum2);
        verify(mockAlbumRepository, times(1)).save(expectedAlbum3);

    }

    @Test
    void putAlbum() {
        // Arrange
        Long id1 = 1L;
        Album existingExpectedAlbum1 = new Album(id1, "Old Album title 1", "Old Artist name 1", GENRE.ROCK, 2001, 10);
        Album updatedExpectedAlbum2 = new Album("New title 2", "New Artist name 2");
        Album savedExpectedAlbum3 = new Album(id1, "New title 2", "New Artist name 2", GENRE.ROCK, 2001, 10);

        when(mockAlbumRepository.findById(id1)).thenReturn(Optional.of(existingExpectedAlbum1));
        when(mockAlbumRepository.save(any(Album.class))).thenReturn(savedExpectedAlbum3);

        // Act
        Album result1 = albumServiceImp.putAlbum(updatedExpectedAlbum2, id1);

        // Assert
        assertThat(result1).isEqualTo(savedExpectedAlbum3);
        verify(mockAlbumRepository, times(1)).findById(id1);
        verify(mockAlbumRepository, times(1)).save(any());


    }
}
