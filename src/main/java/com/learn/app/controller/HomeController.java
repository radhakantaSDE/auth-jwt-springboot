package com.learn.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order/secure/v1")
public class HomeController {

    @GetMapping("{order-id}")
    public ResponseEntity<String> getOrder(@PathVariable("order-id") String orderId) {
        System.out.println("test");
        return ResponseEntity.ok("correct");
    }
}
