package com.example.pos.controller;

import com.example.pos.model.entity.Product;
import com.example.pos.model.request.ProductRequest;
import com.example.pos.model.response.ProductResponse;
import com.example.pos.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    ProductRequest productRequest;
    ProductService productService;

    public ProductController(ProductRequest productRequest, ProductService productService) {
        this.productRequest = productRequest;
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public List<ProductResponse> getListProduct(){
        return productService.getListProduct();
    }

    @PostMapping("/insert")
    public ProductResponse insertProduct(@RequestBody ProductRequest request){
        return productService.insertProduct(request);
    }

    @PostMapping("/update")
    public ProductResponse updateProduct(@RequestBody ProductRequest request){
        return productService.updateProduct(request);
    }

}
