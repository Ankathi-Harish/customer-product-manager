package com.etiqa.customer.controller;

import com.etiqa.customer.model.Customer;
import com.etiqa.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
@Validated  // Enable validation for controller methods
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Mono<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        log.info("Creating customer: {}", customer);
        return customerService.createCustomer(customer)
                .doOnError(error -> log.error("Error creating customer: {}", error.getMessage()));
    }

    @GetMapping
    public Flux<Customer> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Retrieving all customers with page: {}, size: {}", page, size);
        // Assuming pagination is handled in a more advanced manner in a real-world scenario
        return customerService.getAllCustomers(page, size)
                .doOnError(error -> log.error("Error retrieving customers: {}", error.getMessage()));
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomerById(@PathVariable Long id) {
        log.info("Retrieving customer by ID: {}", id);
        return customerService.getCustomerById(id)
                .doOnError(error -> log.error("Error retrieving customer by ID {}: {}", id, error.getMessage()));
    }

    @PutMapping("/{id}")
    public Mono<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails) {
        log.info("Updating customer with ID: {}", id);
        return customerService.updateCustomer(id, customerDetails)
                .doOnError(error -> log.error("Error updating customer with ID {}: {}", id, error.getMessage()));
    }

    @PatchMapping("/{id}")
    public Mono<Customer> patchCustomer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Patching customer with ID: {}", id);
        return customerService.patchCustomer(id, updates)
                .doOnError(error -> log.error("Error patching customer with ID {}: {}", id, error.getMessage()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable Long id) {
        log.info("Deleting customer with ID: {}", id);
        return customerService.deleteCustomer(id)
                .doOnError(error -> log.error("Error deleting customer with ID {}: {}", id, error.getMessage()));
    }
}
