package com.example.product_service.service;

import org.springframework.stereotype.Service;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import java.util.List;

@Service
@RequiredArgsConstructor //from lombok, in compile time constructor will be made
@Slf4j //used to make some logs
public class ProductService {

    //Constructor injection
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .build();

        productRepository.save(product);
        log.info("Product {} is Saved", product.getId());
    }

    public List<ProductResponse> getAllProducts()
    {
        List<Product> products = productRepository.findAll();

        // products.stream().map(product -> mapToProductResponse(product));
        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product)
    {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }
}
