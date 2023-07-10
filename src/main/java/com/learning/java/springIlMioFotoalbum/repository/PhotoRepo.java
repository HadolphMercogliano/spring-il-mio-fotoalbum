package com.learning.java.springIlMioFotoalbum.repository;

import com.learning.java.springIlMioFotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {
  
  Optional<Photo> findByTitle(String name);
  
  List<Photo> findByTitleContainingIgnoreCase(String title);
  
  @Query
    ("SELECT p FROM Photo p WHERE p.visible = true")
  List<Photo> findAllVisiblePhotos();
  
  @Query("SELECT p FROM Photo p WHERE p.visible = true AND (:searchTerm IS NULL OR LOWER(p.title) LIKE %:searchTerm%)")
  List<Photo> findAllVisiblePhotosWithSearchTerm(@Param("searchTerm") String searchTerm);
  
}
