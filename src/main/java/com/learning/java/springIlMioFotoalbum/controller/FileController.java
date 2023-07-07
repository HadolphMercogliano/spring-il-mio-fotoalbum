package com.learning.java.springIlMioFotoalbum.controller;

import com.learning.java.springIlMioFotoalbum.exeptions.PhotoNotFoundExeption;
import com.learning.java.springIlMioFotoalbum.model.Photo;
import com.learning.java.springIlMioFotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {
  @Autowired
  PhotoService photoService;
  
  @GetMapping("/image/{photoId}")
  public ResponseEntity<byte[]> getPhotoFile(@PathVariable Integer photoId) {
    try {
      Photo photo = photoService.getById(photoId);
      MediaType mediaType = MediaType.IMAGE_JPEG;
      return ResponseEntity.ok().contentType(mediaType).body(photo.getImage());
    } catch (PhotoNotFoundExeption e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
