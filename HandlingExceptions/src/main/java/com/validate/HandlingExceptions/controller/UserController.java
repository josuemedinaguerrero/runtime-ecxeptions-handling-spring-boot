package com.validate.HandlingExceptions.controller;

import com.validate.HandlingExceptions.dto.User;
import com.validate.HandlingExceptions.exceptions.BusinessException;
import com.validate.HandlingExceptions.exceptions.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @PostMapping
  public ResponseEntity saveUser(@RequestBody User user) {
    if (user.getEmail().equals("") || user.getEmail() == null) {
      // throw new RuntimeException("Email is required");
      throw new RequestException("P-401", "Email is required");
    }

    if (user.getEmail().equals("test@test.com")) {
      throw new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "Email already exist");
    }
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
  
}
