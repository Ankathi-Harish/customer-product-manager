package com.etiqa.customer;

import com.etiqa.customer.service.CustomerService;
import com.etiqa.customer.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerProductManagerTests {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
		// Verify that the application context loads and the services are available
		assertThat(customerService).isNotNull();
		assertThat(productService).isNotNull();
	}

	@Test
	void checkCustomerServiceBean() {
		// This test ensures that the CustomerService bean is loaded correctly
		assertThat(customerService).isNotNull();
	}

	@Test
	void checkProductServiceBean() {
		// This test ensures that the ProductService bean is loaded correctly
		assertThat(productService).isNotNull();
	}

}
