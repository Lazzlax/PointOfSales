package com.example.pos.service;

import com.example.pos.model.entity.Product;
import com.example.pos.model.request.ProductRequest;
import com.example.pos.model.response.ProductResponse;
import com.example.pos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductResponse productResponse;

    public ProductService(ProductRepository productRepository,ProductResponse productResponse) {
        this.productRepository = productRepository;
        this.productResponse = productResponse;
    }

    public List<ProductResponse> getListProduct(){
        List<ProductResponse> productResponses = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for (Product item:products) {
            ProductResponse prd = ProductResponse.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .build();
            productResponses.add(prd);
        }

        return productResponses;
    }

    public ProductResponse insertProduct(ProductRequest request) {

        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .price(request.getPrice())
                .name(request.getName())
                .quantity(request.getQuantity())
                .build();

        productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse updateProduct(ProductRequest request) {

        Product product = Product.builder()
                .id(request.getId())
                .price(request.getPrice())
                .name(request.getName())
                .quantity(request.getQuantity())
                .build();

        productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
