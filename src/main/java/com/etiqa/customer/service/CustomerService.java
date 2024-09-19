package com.etiqa.customer.service;

import com.etiqa.customer.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CustomerService {

    /**
     * Create a new customer.
     *
     * @param customer - The customer entity to be created.
     * @return Mono<Customer> - A reactive stream containing the created customer.
     */
    Mono<Customer> createCustomer(Customer customer);

    /**
     * Retrieve all customers with pagination.
     *
     * @param page - The page number.
     * @param size - The page size.
     * @return Flux<Customer> - A reactive stream of customers.
     */
    Flux<Customer> getAllCustomers(int page, int size);

    /**
     * Retrieve a customer by ID.
     *
     * @param id - The ID of the customer.
     * @return Mono<Customer> - A reactive stream containing the customer or empty if not found.
     */
    Mono<Customer> getCustomerById(Long id);

    /**
     * Update an existing customer.
     *
     * @param id - The ID of the customer to be updated.
     * @param customerDetails - The details to update.
     * @return Mono<Customer> - A reactive stream containing the updated customer.
     */
    Mono<Customer> updateCustomer(Long id, Customer customerDetails);

    /**
     * Partially update a customer.
     *
     * @param id - The ID of the customer to be updated.
     * @param updates - A map of fields to update.
     * @return Mono<Customer> - A reactive stream containing the updated customer.
     */
    Mono<Customer> patchCustomer(Long id, Map<String, Object> updates);

    /**
     * Delete a customer by ID.
     *
     * @param id - The ID of the customer to be deleted.
     * @return Mono<Void> - A reactive stream indicating completion.
     */
    Mono<Void> deleteCustomer(Long id);
}
