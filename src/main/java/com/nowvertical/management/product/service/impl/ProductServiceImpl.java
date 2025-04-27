package com.nowvertical.management.product.service.impl;

import com.nowvertical.management.product.dto.ProductDto;
import com.nowvertical.management.product.entity.Product;
import com.nowvertical.management.product.exception.ProductNotFoundException;
import com.nowvertical.management.product.repository.ProductRepository;
import com.nowvertical.management.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), LocalDateTime.now(),
                LocalDateTime.now());
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto dto) {
        Product existing = getProductById(id);
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setUpdatedAt(LocalDateTime.now());
        return repository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
