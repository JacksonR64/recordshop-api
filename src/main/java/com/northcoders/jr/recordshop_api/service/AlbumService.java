package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    Album postAlbum(Album album);
    Album putAlbum(Album album, long id);

}
