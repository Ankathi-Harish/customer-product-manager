package com.etiqa.customer.service.impl;

import com.etiqa.customer.model.Customer;
import com.etiqa.customer.repository.CustomerRepository;
import com.etiqa.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        log.info("Creating customer: {}", customer);
        return customerRepository.save(customer)
                .doOnError(error -> log.error("Error creating customer: {}", error.getMessage()));
    }

    @Override
    public Flux<Customer> getAllCustomers(int page, int size) {
        log.info("Retrieving all customers with page: {}, size: {}", page, size);
        // Assuming pagination is handled in a more advanced manner in a real-world scenario
        return customerRepository.findAll()
                .doOnError(error -> log.error("Error retrieving customers: {}", error.getMessage()));
    }

    @Override
    public Mono<Customer> getCustomerById(Long id) {
        log.info("Retrieving customer by ID: {}", id);
        return customerRepository.findById(id)
                .doOnError(error -> log.error("Error retrieving customer by ID {}: {}", id, error.getMessage()));
    }

    @Override
    public Mono<Customer> updateCustomer(Long id, Customer customerDetails) {
        log.info("Updating customer with ID: {}", id);
        return customerRepository.findById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setFirstName(customerDetails.getFirstName());
                    existingCustomer.setLastName(customerDetails.getLastName());
                    existingCustomer.setEmailOffice(customerDetails.getEmailOffice());
                    existingCustomer.setEmailPersonal(customerDetails.getEmailPersonal());
                    existingCustomer.setFamilyMembers(customerDetails.getFamilyMembers());
                    existingCustomer.setDateOfBirth(customerDetails.getDateOfBirth());
                    existingCustomer.setAddress(customerDetails.getAddress());
                    return customerRepository.save(existingCustomer);
                })
                .doOnError(error -> log.error("Error updating customer with ID {}: {}", id, error.getMessage()));
    }

    @Override
    public Mono<Customer> patchCustomer(Long id, Map<String, Object> updates) {
        log.info("Patching customer with ID: {}", id);
        return customerRepository.findById(id)
                .flatMap(customer -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "firstName":
                                customer.setFirstName((String) value);
                                break;
                            case "lastName":
                                customer.setLastName((String) value);
                                break;
                            case "emailOffice":
                                customer.setEmailOffice((String) value);
                                break;
                            case "emailPersonal":
                                customer.setEmailPersonal((String) value);
                                break;
                            case "familyMembers":
                                customer.setFamilyMembers((String) value);
                                break;
                            case "dateOfBirth":
                                customer.setDateOfBirth((String) value);
                                break;
                            case "address":
                                customer.setAddress((String) value);
                                break;
                        }
                    });
                    return customerRepository.save(customer);
                })
                .doOnError(error -> log.error("Error patching customer with ID {}: {}", id, error.getMessage()));
    }

    @Override
    public Mono<Void> deleteCustomer(Long id) {
        log.info("Deleting customer with ID: {}", id);
        return customerRepository.deleteById(id)
                .doOnError(error -> log.error("Error deleting customer with ID {}: {}", id, error.getMessage()));
    }
}
