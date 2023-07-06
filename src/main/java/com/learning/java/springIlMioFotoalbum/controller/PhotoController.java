package com.learning.java.springIlMioFotoalbum.controller;

import com.learning.java.springIlMioFotoalbum.model.Photo;
import com.learning.java.springIlMioFotoalbum.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {
  @Autowired
  private PhotoRepo photoRepo;
  @GetMapping
  public String index(Model model) {
    List<Photo> photos = photoRepo.findAll();
    model.addAttribute("photo", photos);
    return "/photos/index";
  }
}
