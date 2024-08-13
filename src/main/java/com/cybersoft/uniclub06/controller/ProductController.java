package com.cybersoft.uniclub06.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @PostMapping
    public ResponseEntity<?> addProduct() {
        return new ResponseEntity<>("Hello add Product", HttpStatus.OK);
    }
}
