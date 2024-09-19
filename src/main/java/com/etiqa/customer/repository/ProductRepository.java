package com.etiqa.customer.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import com.etiqa.customer.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends R2dbcRepository<Product, Long> {

    // Custom query methods can be added here if needed
    Flux<Product> findByBookAuthor(String bookAuthor);

    Flux<Product> findByLanguage(String language);

    Mono<Product> findByBookTitle(String bookTitle);
}
