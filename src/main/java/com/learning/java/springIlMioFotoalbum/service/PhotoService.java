package com.learning.java.springIlMioFotoalbum.service;

import com.learning.java.springIlMioFotoalbum.dto.PhotoForm;
import com.learning.java.springIlMioFotoalbum.exeptions.NotUniqueTitleExeption;
import com.learning.java.springIlMioFotoalbum.exeptions.PhotoNotFoundExeption;
import com.learning.java.springIlMioFotoalbum.model.Photo;
import com.learning.java.springIlMioFotoalbum.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
  
  @Autowired
  PhotoRepo photoRepo;
  
  public List<Photo> getAll(Optional<String> keywordOpt) {
    if (keywordOpt.isEmpty()) {
      return photoRepo.findAll();
    }else {
      return photoRepo.findByTitleContainingIgnoreCase(keywordOpt.get());
    }
  }
  public Photo getById(Integer id) throws PhotoNotFoundExeption {
    Optional<Photo> photoOpt = photoRepo.findById(id);
    if (photoOpt.isPresent()) {
      return photoOpt.get();
    } else {
      throw new PhotoNotFoundExeption("La foto con id " + id + " non è stata trovata");
    }
  }
  
  public Photo create(Photo photo) throws NotUniqueTitleExeption {
    if (!isUniqueTitle(photo)) {
      throw new NotUniqueTitleExeption(photo.getTitle());
    }
    Photo photoToPersist = new Photo();
    photoToPersist.setTitle(photo.getTitle());
    photoToPersist.setDescription(photo.getDescription());
    photoToPersist.setCategories(photo.getCategories());
    photoToPersist.setVisible(photo.isVisible());
    return photoRepo.save(photoToPersist);
  }
  
  public Photo create(PhotoForm photoForm){
    Photo photo = mapPhotoFormToPhoto(photoForm);
    return create(photo);
  }
  
  public PhotoForm getPhotoFormById(Integer id) throws PhotoNotFoundExeption {
    Photo photo = getById(id);
    return mapPhotoToPhotoForm(photo);
  }
  
  public Photo update(PhotoForm photoForm) throws PhotoNotFoundExeption, NotUniqueTitleExeption {
    Photo photo = mapPhotoFormToPhoto(photoForm);
    Photo photoDb = getById(photo.getId());
    if (!photo.getTitle().equals(photoDb.getTitle()) && !isUniqueTitle(photo)) {
      throw  new NotUniqueTitleExeption(photoDb.getTitle());
    }
    photoDb.setTitle(photo.getTitle());
    photoDb.setDescription(photo.getDescription());
    photoDb.setVisible(photo.isVisible());
    photoDb.setCategories(photo.getCategories());
    return photoRepo.save(photoDb);
  }
  
  
  
//  METODI UTILITY
  public Photo getPhotoById(Integer id){
    Optional<Photo> result = photoRepo.findById(id);
    if(result.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "foto con id " + id + " non trovata");
    }
    return result.get();
  }
  private boolean isUniqueTitle(Photo formPhoto){
    Optional<Photo> result = photoRepo.findByTitle(formPhoto.getTitle());
    return result.isEmpty();
  }
  private Photo mapPhotoFormToPhoto(PhotoForm photoForm){
    Photo photo = new Photo();
    
    photo.setId(photoForm.getId());
    photo.setTitle(photoForm.getTitle());
    photo.setDescription(photoForm.getDescription());
    photo.setUrl(photoForm.getUrl());
    photo.setVisible(photoForm.isVisible());
    photo.setCategories(photoForm.getCategories());
    
//    byte[] coverBytes = multipartFileToByteArray(pizzaForm.getCoverFile());
//    pizza.setCover(coverBytes);
    
    return photo;
  }
  private PhotoForm mapPhotoToPhotoForm(Photo photo){
    PhotoForm photoForm = new PhotoForm();
    
    photoForm.setId(photo.getId());
    photoForm.setTitle(photo.getTitle());
    photoForm.setDescription(photo.getDescription());
    photoForm.setVisible(photo.isVisible());
    photoForm.setCategories(photo.getCategories());
    
    return photoForm;
  }
  
  
}
