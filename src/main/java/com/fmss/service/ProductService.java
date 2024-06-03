package com.fmss.service;

import com.fmss.model.Product;
import com.fmss.repository.repositories.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;
    private Long productId = 0L;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        product.setId(++productId);
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void findAll() {
        productRepository.findAll().forEach(System.out::println);
    }

    public Product findById(Long id) {
        return productRepository.findAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
