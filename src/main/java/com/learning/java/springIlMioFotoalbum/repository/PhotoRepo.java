package com.learning.java.springIlMioFotoalbum.repository;

import com.learning.java.springIlMioFotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {
  
  Optional<Photo> findByTitle(String name);
  
  List<Photo> findByTitleContainingIgnoreCase(String title);
}
