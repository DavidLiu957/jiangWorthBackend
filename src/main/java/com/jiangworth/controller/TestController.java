package com.jiangworth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
	
	@GetMapping("/hello")
    public ResponseEntity<String> getAllUsers() {
		System.out.println("Hello!");
        return ResponseEntity.ok("Hello!");
    }

}
