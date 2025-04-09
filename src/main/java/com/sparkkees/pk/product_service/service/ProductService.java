package com.sparkkees.pk.product_service.service;

import com.sparkkees.pk.product_service.dto.ProductDTO;
import com.sparkkees.pk.product_service.model.Product;
import com.sparkkees.pk.product_service.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = toModel(productDTO);
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    private static Product toModel(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        return product;
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @CacheEvict(value = "products", key = "#id")
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        productRepository.save(product); // save triggers update
    }
}
