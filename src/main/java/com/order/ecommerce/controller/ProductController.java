package com.order.ecommerce.controller;

import com.order.ecommerce.dto.ProductDTO;
import com.order.ecommerce.model.Product;
import com.order.ecommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    @Operation(summary = "Create a product", description = "Create a product")
    public Product createOrder(@RequestBody ProductDTO productDTO)
    {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/products/{productId}")
    @Operation(summary = "Get a product", description = "Get a product")
    public ProductDTO getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }
}
