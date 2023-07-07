package com.learning.java.springIlMioFotoalbum.service;

import com.learning.java.springIlMioFotoalbum.exeptions.NotUniqueTitleExeption;
import com.learning.java.springIlMioFotoalbum.model.Message;
import com.learning.java.springIlMioFotoalbum.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
  @Autowired
  MessageRepo messageRepo;
  
  public Message create(Message message) {
    
    Message messageToPersist = new Message();
    messageToPersist.setEmail(message.getEmail());
    messageToPersist.setMessage(message.getMessage());
    
    return messageRepo.save(messageToPersist);
  }
}
