package com.sparkkees.pk.product_service.controller;

import com.sparkkees.pk.product_service.dto.ProductDTO;
import com.sparkkees.pk.product_service.model.Product;
import com.sparkkees.pk.product_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        URI uri = URI.create("/products/" + product.getId());
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO.getId(), productDTO);
        return ResponseEntity.ok().build();
    }
}