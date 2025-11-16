package com.example.warehouse_castores.controller;

import com.example.warehouse_castores.dto.product.ProductDTO;
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
    public List<Product> getAllProducts(
            @RequestParam(required = false) Boolean status
    ) {
        return productService.getAllProducts(status);
    }

    @PostMapping("")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{idProduct}")
    public Product updateProduct(
            @PathVariable Long idProduct,
            @Valid @RequestBody ProductDTO productData
    ) {
        return productService.updateProduct(idProduct, productData);
    }

    @PatchMapping("/{idProduct}")
    public Product updateProductQuantity(
            @PathVariable Long idProduct,
            @Valid @RequestBody ProductDTO productData
    ) {
        return productService.updateProductQuantity(idProduct, productData);
    }
}
