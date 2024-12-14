package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AlbumCacheService albumCacheService;

    @Override
    public List<Album> getAllAlbums() {
        return new ArrayList<>(albumRepository.findAll());
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumCacheService.getByIdWithCache(id).orElseThrow(() -> new RuntimeException("Album not found"));
/*
                albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
*/
    }

    @Override
    public Album postAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album putAlbum(Album album, long id) {
        var maybeAlbum = albumRepository.findById(id);
        if (maybeAlbum.isEmpty()) return null; //TODO
        album.setId(id);

        albumCacheService.resetCacheId(id);
        return albumRepository.save(album);
    }
}
