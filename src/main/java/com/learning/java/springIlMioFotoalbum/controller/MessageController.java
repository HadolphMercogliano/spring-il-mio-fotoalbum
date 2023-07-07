package com.learning.java.springIlMioFotoalbum.controller;

import com.learning.java.springIlMioFotoalbum.alertMessages.AlertMessage;
import com.learning.java.springIlMioFotoalbum.alertMessages.AlertMessageType;
import com.learning.java.springIlMioFotoalbum.model.Message;
import com.learning.java.springIlMioFotoalbum.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/message")
public class MessageController {
  
  @Autowired
  MessageRepo messageRepo;
  
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    Message messageToDelete= getMessageById(id);
    messageRepo.delete(messageToDelete);
    redirectAttributes.addFlashAttribute("message",new AlertMessage(AlertMessageType.SUCCESS, "Il messaggio inviato da " + messageToDelete.getEmail() + " è stato eliminato"));
    return "redirect:/photos";
  }
  
  
//  UTILITY
  private Message getMessageById(Integer id){
    Optional<Message> result = messageRepo.findById(id);
    if(result.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "messaggio con id " + id + " non trovato");
    }
    return result.get();
  }
}
