package com.learning.java.springIlMioFotoalbum.controller;

import com.learning.java.springIlMioFotoalbum.dto.PhotoForm;
import com.learning.java.springIlMioFotoalbum.exeptions.NotUniqueTitleExeption;
import com.learning.java.springIlMioFotoalbum.exeptions.PhotoNotFoundExeption;
import com.learning.java.springIlMioFotoalbum.alertMessages.AlertMessage;
import com.learning.java.springIlMioFotoalbum.alertMessages.AlertMessageType;
import com.learning.java.springIlMioFotoalbum.model.Message;
import com.learning.java.springIlMioFotoalbum.model.Photo;
import com.learning.java.springIlMioFotoalbum.repository.CategoryRepo;
import com.learning.java.springIlMioFotoalbum.repository.MessageRepo;
import com.learning.java.springIlMioFotoalbum.repository.PhotoRepo;
import com.learning.java.springIlMioFotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
  @Autowired
  private PhotoRepo photoRepo;
  
  @Autowired
  private CategoryRepo categoryRepo;
  @Autowired
  private MessageRepo messageRepo;
  
  
  @Autowired
  PhotoService photoService;
  
  @GetMapping
  public String index(@RequestParam Optional<String> keyword, Model model) {
  
    //  prende tutte le foto dal database e le aggiungo al model
    //  oppure le filtra in base alla ricerca dell' utente e mostra i risultati.
    List<Photo> photos = photoService.getAll(keyword);
    List<Message> messages = messageRepo.findAll();
    
    model.addAttribute("photos", photos);
    model.addAttribute("messages", messages);
    return "/photos/index";
  }
  
  @GetMapping("/{id}")
  public String details(@PathVariable("id") Integer id, Model model) {
    Photo photo = photoService.getPhotoById(id);
    model.addAttribute("photo", photo);
    return "/photos/details";
  }
  
  //  chiede la view per aggiungere una nuova foto
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("photo",new PhotoForm());
    model.addAttribute("categoryList", categoryRepo.findAll());
    return "/photos/edit";
  }
  
  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("photo") PhotoForm photoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
    if (!bindingResult.hasErrors()) {
      try {
        photoService.create(photoForm);
      } catch (NotUniqueTitleExeption e) {
        bindingResult.addError(new FieldError("photo", "title",photoForm.getTitle(),false,null,null, "il titolo deve essere unico" ));
      }
    }
    if(bindingResult.hasErrors()) {
      model.addAttribute("categoryList", categoryRepo.findAll());
      return "/photos/edit";
    }
    redirectAttributes.addFlashAttribute("message",
      new AlertMessage(AlertMessageType.SUCCESS, "Foto creata!"));
    return "redirect:/photos";
  }
  
  //  chiede la view del modulo per modificare la foto
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    try {
      PhotoForm photoForm = photoService.getPhotoFormById(id);
      model.addAttribute("photo", photoForm);
      model.addAttribute("categoryList", categoryRepo.findAll());
      return "/photos/edit";
    } catch (PhotoNotFoundExeption e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
  
  //  invia e salva la modifica della foto
  @PostMapping("/edit/{id}")
  public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") PhotoForm photoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
    if (!bindingResult.hasErrors()) {
      try {
        photoService.update(photoForm);
      } catch (PhotoNotFoundExeption e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      } catch (NotUniqueTitleExeption e) {
        bindingResult.addError(new FieldError("photo","title", photoForm.getTitle(),false,null,null, "Il titolo deve essere unico" ));
      }
    }
    if (bindingResult.hasErrors()) {
      model.addAttribute("categoryList", categoryRepo.findAll());
      return "/photos/edit";
    }
    
    redirectAttributes.addFlashAttribute("message",
      new AlertMessage(AlertMessageType.SUCCESS, "Foto modificata con successo!"));
    return "redirect:/photos";
  }
  
  //  cancella la pizza
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    Photo photoToDelete= photoService.getPhotoById(id);
    photoRepo.delete(photoToDelete);
    redirectAttributes.addFlashAttribute("message",new AlertMessage(AlertMessageType.SUCCESS, "Foto " + photoToDelete.getTitle() + " eliminata"));
    return "redirect:/photos";
  }
  
}
