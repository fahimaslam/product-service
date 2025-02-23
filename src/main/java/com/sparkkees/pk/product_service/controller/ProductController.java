package com.sparkkees.pk.product_service.controller;

import com.sparkkees.pk.product_service.model.Product;
import com.sparkkees.pk.product_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> products(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
