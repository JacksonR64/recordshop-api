package com.northcoders.jr.recordshop_api.repository;

import com.northcoders.jr.recordshop_api.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long > {
}
