package com.learning.java.springIlMioFotoalbum.controller;

import com.learning.java.springIlMioFotoalbum.model.Category;
import com.learning.java.springIlMioFotoalbum.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  }
