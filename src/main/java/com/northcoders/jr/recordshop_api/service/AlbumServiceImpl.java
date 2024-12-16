package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        return new ArrayList<>(albumRepository.findAll());
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
    }

    @Override
    public Album postAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album putAlbum(Long id, Album album) {
        if (albumRepository.findById(id).isPresent()){
            System.out.println("Put album found!!!");
            System.out.println(album);
            albumRepository.findById(id).get().setTitle(album.getTitle());
            albumRepository.findById(id).get().setId(id);
            albumRepository.findById(id).get().setArtist(album.getArtist());
            albumRepository.findById(id).get().setGenre(album.getGenre());
            albumRepository.findById(id).get().setReleaseYear(album.getReleaseYear());
            albumRepository.findById(id).get().setStockCount(album.getStockCount());
            return albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        }else {
            throw new RuntimeException("Album not found");
        }




    }

    @Override
    public Album patchAlbum(Long id, Album partialUpdate) {
        // Find the album by ID
        Album existingAlbum = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        // Update fields if they are provided in the partial update
        if (partialUpdate.getTitle() != null) {
            existingAlbum.setTitle(partialUpdate.getTitle());
        }
        if (partialUpdate.getArtist() != null) {
            existingAlbum.setArtist(partialUpdate.getArtist());
        }
        if (partialUpdate.getReleaseYear() != null) {
            existingAlbum.setReleaseYear(partialUpdate.getReleaseYear());
        }
        if (partialUpdate.getGenre() != null) {
            existingAlbum.setGenre(partialUpdate.getGenre());
        }

        // Save the updated album
        return albumRepository.save(existingAlbum);
    }

    @Override
    public Album deleteAlbum(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
        albumRepository.delete(album);
        return album;
    }
}
