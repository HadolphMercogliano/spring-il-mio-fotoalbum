package com.learning.java.springIlMioFotoalbum.repository;

import com.learning.java.springIlMioFotoalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
