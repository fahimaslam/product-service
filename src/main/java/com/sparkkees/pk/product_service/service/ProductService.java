package com.sparkkees.pk.product_service.service;

import com.sparkkees.pk.product_service.model.Product;
import com.sparkkees.pk.product_service.repositories.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @CacheEvict(value = "products", key = "#id")
    public void updateProduct(Long id, Product newProduct) {
        productRepository.save(newProduct);
    }
}
