package com.northcoders.jr.recordshop_api.controller;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getBookById (@PathVariable long id){
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Album> postAlbum(@RequestBody Album album) {
        Album newAlbum = albumService.postAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/album/" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> putAlbum(@PathVariable long id, @RequestBody Album album) {
        Album newAlbum = albumService.putAlbum(id, album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/album/" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Album> patchAlbum (@PathVariable Long id, @RequestBody Album partialUpdate) {
        Album updatedAlbum = albumService.patchAlbum(id, partialUpdate);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteBookById (@PathVariable long id){
        return new ResponseEntity<>(albumService.deleteAlbum(id), HttpStatus.NO_CONTENT);
    }

}
