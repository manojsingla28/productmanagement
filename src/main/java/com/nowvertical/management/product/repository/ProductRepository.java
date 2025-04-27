package com.nowvertical.management.product.repository;

import com.nowvertical.management.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}