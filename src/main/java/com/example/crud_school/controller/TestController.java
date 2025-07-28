package com.example.crud_school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping
    public String test() {
        return "School Database CRUD Application is running!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "Application is healthy!";
    }
} 