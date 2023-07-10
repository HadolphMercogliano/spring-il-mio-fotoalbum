package com.learning.java.springIlMioFotoalbum.model;

import java.util.List;

public class PhotoResponse {
    private List<Photo> photos;
    private List<Category> categories;
    
    public PhotoResponse(List<Photo> photos, List<Category> categories) {
      this.photos = photos;
      this.categories = categories;
    }
    
    // Getters e Setters
  
  public List<Photo> getPhotos() {
    return photos;
  }
  
  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }
  
  public List<Category> getCategories() {
    return categories;
  }
  
  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
