package com.example.warehouse_castores.controller;

import com.example.warehouse_castores.model.Product;
import com.example.warehouse_castores.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }
}
