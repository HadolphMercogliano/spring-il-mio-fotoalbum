package com.learning.java.springIlMioFotoalbum.controller;

import com.learning.java.springIlMioFotoalbum.model.Category;
import com.learning.java.springIlMioFotoalbum.model.Photo;
import com.learning.java.springIlMioFotoalbum.repository.CategoryRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
  
  @Autowired
  private CategoryRepo categoryRepo;
  
  @GetMapping
  public String index(Model model, @RequestParam("edit") Optional<Integer> categoryId) {
    List<Category> categoryList = categoryRepo.findAll();
    model.addAttribute("categories", categoryList);
    
    Category categoryObj;
    if (categoryId.isPresent()) {
      Optional<Category> categoryDB = categoryRepo.findById(categoryId.get());
      if (categoryDB.isPresent()) {
        categoryObj = categoryDB.get();
      } else {
        categoryObj = new Category();
      }
    } else {
      categoryObj = new Category();
    }
    model.addAttribute("categoryObj", categoryObj);
    return "/categories/index";
  }
  
  @PostMapping("/save")
  public String save(@Valid @ModelAttribute("categoryObj") Category formCategory, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("categories", categoryRepo.findAll());
      return "/categories/index";
    }
    categoryRepo.save(formCategory);
    return "redirect:/categories";
  }
  
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    // prima di eliminare la categoria bisogna dissociarla dalle foto.
    Optional<Category> result = categoryRepo.findById(id);
    if (result.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    Category categoryToDelete = result.get();
    for (Photo photo : categoryToDelete.getPhotos()) {
      photo.getCategories().remove(categoryToDelete);
    }
    categoryRepo.deleteById(id);
    return "redirect:/categories";
  }
}
