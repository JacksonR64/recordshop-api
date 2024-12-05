package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{
    @Override
    public List<Album> getAllAlbums() {
        return List.of();
    }

    @Override
    public Album postAlbum(Album album) {
        return null;
    }
}
