package com.nowvertical.management.product.service;

import com.nowvertical.management.product.dto.ProductDto;
import com.nowvertical.management.product.entity.Product;
import com.nowvertical.management.product.repository.ProductRepository;
import com.nowvertical.management.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductDto dto = new ProductDto();
        dto.setName("Test Product");
        dto.setDescription("Description");
        dto.setPrice(new BigDecimal("10.00"));

        Product product = Product.builder()
                .id(1L)
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(repository.save(any(Product.class))).thenReturn(product);

        Product created = service.createProduct(dto);

        assertEquals("Test Product", created.getName());
        verify(repository, times(1)).save(any(Product.class));
    }
}
