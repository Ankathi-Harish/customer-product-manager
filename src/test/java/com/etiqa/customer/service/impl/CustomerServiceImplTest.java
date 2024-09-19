package com.etiqa.customer.service.impl;

import com.etiqa.customer.model.Customer;
import com.etiqa.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(customer));

        Mono<Customer> result = customerService.createCustomer(customer);

        StepVerifier.create(result)
                .expectNextMatches(c -> c.getId().equals(1L))
                .verifyComplete();
        verify(customerRepository).save(customer);
    }

    @Test
    void getAllCustomers() {
        Customer customer = new Customer();
        customer.setId(1L);
        Flux<Customer> customerFlux = Flux.just(customer);
        when(customerRepository.findAll()).thenReturn(customerFlux);

        Flux<Customer> result = customerService.getAllCustomers(0, 10);

        StepVerifier.create(result)
                .expectNextMatches(c -> c.getId().equals(1L))
                .verifyComplete();
        verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(anyLong())).thenReturn(Mono.just(customer));

        Mono<Customer> result = customerService.getCustomerById(1L);

        StepVerifier.create(result)
                .expectNextMatches(c -> c.getId().equals(1L))
                .verifyComplete();
        verify(customerRepository).findById(1L);
    }

    @Test
    void updateCustomer() {
        Customer existingCustomer = new Customer();
        existingCustomer.setId(1L);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1L);
        updatedCustomer.setFirstName("Harish");
        updatedCustomer.setLastName("Ankathi");

        when(customerRepository.findById(anyLong())).thenReturn(Mono.just(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(updatedCustomer));

        Mono<Customer> result = customerService.updateCustomer(1L, updatedCustomer);

        StepVerifier.create(result)
                .expectNextMatches(c -> "Harish".equals(c.getFirstName()) && "Ankathi".equals(c.getLastName()))
                .verifyComplete();
        verify(customerRepository).findById(1L);
        verify(customerRepository).save(updatedCustomer);
    }

    @Test
    void patchCustomer() {
        Customer existingCustomer = new Customer();
        existingCustomer.setId(1L);
        existingCustomer.setFirstName("OldName");

        Map<String, Object> updates = new HashMap<>();
        updates.put("firstName", "NewName");

        when(customerRepository.findById(anyLong())).thenReturn(Mono.just(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(existingCustomer));

        Mono<Customer> result = customerService.patchCustomer(1L, updates);

        StepVerifier.create(result)
                .expectNextMatches(c -> "NewName".equals(c.getFirstName()))
                .verifyComplete();
        verify(customerRepository).findById(1L);
        verify(customerRepository).save(existingCustomer);
    }

    @Test
    void deleteCustomer() {
        when(customerRepository.deleteById(anyLong())).thenReturn(Mono.empty());

        Mono<Void> result = customerService.deleteCustomer(1L);

        StepVerifier.create(result)
                .verifyComplete();
        verify(customerRepository).deleteById(1L);
    }
}
