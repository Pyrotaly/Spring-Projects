package com.example.product_service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Assertions;



import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	// integration tests
	@Container //Junit 5 now knows this is a MDB container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.12"); // have to specify MongoDB version

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	// test will start DB container by donwloading mongo to image, then get replica set uri and spring data property at time of test
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry)
	{
		dymDynamicPropertyRegistry.add("spring.data.mongod.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
                .andExpect(status().isCreated()
			);
		// Assertions.assertTrue(productRepository.findAll().size() == 1); //Becuase we only makin one..., should check that
		// Somehow, i am continously using the same docker image on every run.... so the size in the repo is equal to
		// the number of times i ran this
    }

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
			.name("Test Product 89")
			.description("Test Product Description haha :)")
			.price(BigDecimal.valueOf(9999))
			.build();
	}

}
