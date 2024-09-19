package com.etiqa.customer.service;

import com.etiqa.customer.model.Product;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface ProductService {

    Mono<Product> createProduct(Product product);

    Flux<Product> getAllProducts();

    Mono<Product> getProductById(Long id);

    Mono<Product> updateProduct(Long id, Product productDetails);

    Mono<Product> patchProduct(Long id, Map<String, Object> updates);

    Mono<Void> deleteProduct(Long id);
}
