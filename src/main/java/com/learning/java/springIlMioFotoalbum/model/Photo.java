package com.learning.java.springIlMioFotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @NotBlank(message = "Il titolo è obbligatorio")
  @Size(max = 255, message = "Il  titolo deve essere di massimo 255 caratteri")
  @Column(nullable = false, unique = true)
  private String title;
  
  @Size(max = 255, message = "La descrizione deve essere di massimo 255 caratteri")
  private String description;
  
  @Lob
  @Column(length= 16777215)
  private byte[] image;
  
  
  @Column(nullable = false, columnDefinition = "boolean default true")
  private boolean visible;
  
  @ManyToMany
  @JoinTable(
    name = "photo_category",
    joinColumns = @JoinColumn(name = "photo_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<Category> categories = new ArrayList<>();
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public byte[] getImage() {
    return image;
  }
  
  public void setImage(byte[] image) {
    this.image = image;
  }
  
  public boolean isVisible() {
    return visible;
  }
  
  public void setVisible(boolean visible) {
    this.visible = visible;
  }
  
  public List<Category> getCategories() {
    return categories;
  }
  
  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
