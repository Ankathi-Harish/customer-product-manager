package com.etiqa.customer.service.impl;

import com.etiqa.customer.model.Product;
import com.etiqa.customer.repository.ProductRepository;
import com.etiqa.customer.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        StepVerifier.create(productService.createProduct(product))
                .expectNextMatches(createdProduct -> {
                    return createdProduct.getId().equals(1L);
                })
                .verifyComplete();
    }

    @Test
    void getAllProducts() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findAll()).thenReturn(Flux.just(product));

        StepVerifier.create(productService.getAllProducts())
                .expectNextMatches(p -> p.getId().equals(1L))
                .verifyComplete();
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(anyLong())).thenReturn(Mono.just(product));

        StepVerifier.create(productService.getProductById(1L))
                .expectNextMatches(p -> p.getId().equals(1L))
                .verifyComplete();
    }

    @Test
    void updateProduct() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setBookTitle("New Title");

        when(productRepository.findById(anyLong())).thenReturn(Mono.just(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(updatedProduct));

        StepVerifier.create(productService.updateProduct(1L, updatedProduct))
                .expectNextMatches(p -> p.getBookTitle().equals("New Title"))
                .verifyComplete();
    }

    @Test
    void patchProduct() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setBookTitle("Old Title");

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setBookTitle("Updated Title");

        when(productRepository.findById(anyLong())).thenReturn(Mono.just(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(updatedProduct));

        StepVerifier.create(productService.patchProduct(1L, Map.of("bookTitle", "Updated Title")))
                .expectNextMatches(p -> p.getBookTitle().equals("Updated Title"))
                .verifyComplete();
    }

    @Test
    void deleteProduct() {
        when(productRepository.deleteById(anyLong())).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1L))
                .verifyComplete();
    }
}
