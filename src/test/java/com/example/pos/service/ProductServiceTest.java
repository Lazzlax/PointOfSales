package com.example.pos.service;

import com.example.pos.model.entity.Product;
import com.example.pos.model.request.ProductRequest;
import com.example.pos.model.response.ProductResponse;
import com.example.pos.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    Product product;

    @InjectMocks
    ProductService productService;

    private ProductRequest request = ProductRequest.builder()
            .name("Sabun")
            .quantity(3)
            .price(new BigDecimal(10000))
            .build();


    private ProductResponse response = ProductResponse.builder()
            .name("Sabun")
            .quantity(3)
            .price(new BigDecimal(10000))
            .build();

    @BeforeEach
    public void setup(){
        initMocks(this);
    }

    @Test
    public void insertProduct(){
        ProductResponse actualResponse = productService.insertProduct(request);
        response.setId(actualResponse.getId());

        assertEquals(actualResponse,response);
    }

    @Test
    public void updateProduct(){

    }
  
}