package com.etiqa.customer.controller;

import com.etiqa.customer.model.Product;
import com.etiqa.customer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@Valid @RequestBody Product product) {
        log.info("Creating product: {}", product);
        return productService.createProduct(product)
                .map(createdProduct -> ResponseEntity.ok(createdProduct))
                .onErrorResume(e -> {
                    log.error("Error creating product: {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        log.info("Retrieving all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Long id) {
        log.info("Retrieving product by ID: {}", id);
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product productDetails) {
        log.info("Updating product with ID: {}", id);
        return productService.updateProduct(id, productDetails)
                .map(updatedProduct -> ResponseEntity.ok(updatedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Product>> patchProduct(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        log.info("Patching product with ID: {}", id);
        return productService.patchProduct(id, updates)
                .map(patchedProduct -> ResponseEntity.ok(patchedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with ID: {}", id);
        return productService.deleteProduct(id)
                .doOnError(error -> log.error("Error deleting product with ID {}: {}", id, error.getMessage()));
    }
}
