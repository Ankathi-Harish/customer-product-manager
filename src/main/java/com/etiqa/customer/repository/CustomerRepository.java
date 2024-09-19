package com.etiqa.customer.repository;

import com.etiqa.customer.model.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
    Flux<Customer> findByLastName(String lastname);

    // Custom query method to find a customer by email
    Mono<Customer> findByEmailPersonal(String email);
}
