package com.learning.java.springIlMioFotoalbum.api;
import com.learning.java.springIlMioFotoalbum.exeptions.PhotoNotFoundExeption;
import com.learning.java.springIlMioFotoalbum.model.Category;
import com.learning.java.springIlMioFotoalbum.model.Message;
import com.learning.java.springIlMioFotoalbum.model.Photo;
import com.learning.java.springIlMioFotoalbum.model.PhotoResponse;
import com.learning.java.springIlMioFotoalbum.repository.CategoryRepo;
import com.learning.java.springIlMioFotoalbum.repository.MessageRepo;
import com.learning.java.springIlMioFotoalbum.repository.PhotoRepo;
import com.learning.java.springIlMioFotoalbum.service.MessageService;
import com.learning.java.springIlMioFotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/photos")
public class PhotoRestController {
  
  @Autowired
  private PhotoRepo photoRepo;
  
  @Autowired
  private PhotoService  photoService;
  @Autowired
  private MessageRepo messageRepo;
  @Autowired
  private MessageService messageService;
  @Autowired
  private CategoryRepo categoryRepo;
  
//  @GetMapping
//  public List<Photo> index(@RequestParam Optional<String> keyword) {
//    if (keyword.isPresent()) {
//      return photoRepo.findAllVisiblePhotosWithSearchTerm(keyword.get());
//    } else {
//      return photoRepo.findAllVisiblePhotos();
//    }
//  }
  @GetMapping
  public PhotoResponse index(@RequestParam Optional<String> keyword) {
    List<Photo> photos;
    if (keyword.isPresent()) {
      photos = photoRepo.findAllVisiblePhotosWithSearchTerm(keyword.get());
    } else {
      photos = photoRepo.findAllVisiblePhotos();
    }
    List<Category> categories = categoryRepo.findAll(); // Sostituisci "categoryRepo" con il tuo repository per le categorie
    return new PhotoResponse(photos, categories);
  }
  
  @GetMapping("/{id}")
  public Photo get(@PathVariable Integer id) {
    try {
      return photoService.getById(id);
    } catch (PhotoNotFoundExeption e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
  @PostMapping("/message")
  public Message create(@Valid @RequestBody Message message) {
      return messageService.create(message);
    }
  
//  CODICE DI TEST

//  @GetMapping("/messageindex")
//  public List<Message> index(){
//    return messageRepo.findAll();
//  }

}
