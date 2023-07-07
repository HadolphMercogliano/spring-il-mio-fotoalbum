package com.learning.java.springIlMioFotoalbum.repository;

import com.learning.java.springIlMioFotoalbum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {
}
