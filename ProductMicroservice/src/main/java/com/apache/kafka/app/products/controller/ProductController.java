package com.apache.kafka.app.products.controller;

import com.apache.kafka.app.products.dtos.CreateProductRequest;
import com.apache.kafka.app.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/1.0/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) throws Exception {
        String productId = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
