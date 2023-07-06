package com.learning.java.springIlMioFotoalbum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @NotNull
  @Size(min = 2, max = 80)
  @Column(nullable = false)
  private String name;
  
  @JsonIgnore
  @ManyToMany(mappedBy = "categories")
  private List<Photo> photos = new ArrayList<>();
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<Photo> getPhotos() {
    return photos;
  }
  
  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }
}
