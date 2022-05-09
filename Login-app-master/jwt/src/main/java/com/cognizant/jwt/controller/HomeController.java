package com.cognizant.jwt.controller;

import com.cognizant.jwt.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class HomeController {
    private UserRepository userRepository;
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(){
        System.out.println("INside method");
        return ResponseEntity.ok().body(userRepository.findAll());
    }
}

