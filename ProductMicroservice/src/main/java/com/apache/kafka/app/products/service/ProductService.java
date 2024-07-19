package com.apache.kafka.app.products.service;

import com.apache.kafka.app.products.dtos.CreateProductRequest;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    public String createProduct(CreateProductRequest request) throws ExecutionException, InterruptedException, Exception;

}
