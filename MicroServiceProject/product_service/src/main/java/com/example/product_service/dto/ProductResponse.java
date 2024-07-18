package com.example.product_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {  
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
//why we make a sepearte class? Well apparently it is a good practice to seperate model entities and dtos