package com.etiqa.customer.service.impl;

import com.etiqa.customer.model.Product;
import com.etiqa.customer.repository.ProductRepository;
import com.etiqa.customer.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> createProduct(Product product) {
        log.info("Creating product: {}", product);
        return productRepository.save(product);
    }

    @Override
    public Flux<Product> getAllProducts() {
        log.info("Retrieving all products");
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        log.info("Retrieving product by ID: {}", id);
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    @Override
    public Mono<Product> updateProduct(Long id, Product productDetails) {
        log.info("Updating product with ID: {}", id);
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setBookTitle(productDetails.getBookTitle());
                    product.setBookPrice(productDetails.getBookPrice());
                    product.setBookQuantity(productDetails.getBookQuantity());
                    product.setBookAuthor(productDetails.getBookAuthor());
                    product.setLanguage(productDetails.getLanguage());
                    product.setRating(productDetails.getRating());
                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Product> patchProduct(Long id, Map<String, Object> updates) {
        log.info("Patching product with ID: {}", id);
        return productRepository.findById(id)
                .flatMap(product -> {
                    if (updates.containsKey("bookTitle")) {
                        product.setBookTitle((String) updates.get("bookTitle"));
                    }
                    // Apply other updates...
                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Void> deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        return productRepository.deleteById(id);
    }
}
