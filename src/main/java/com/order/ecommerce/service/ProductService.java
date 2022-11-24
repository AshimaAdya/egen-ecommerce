package com.order.ecommerce.service;

import com.order.ecommerce.dto.ProductDTO;
import com.order.ecommerce.exceptionhandler.SystemException;
import com.order.ecommerce.model.Product;
import com.order.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(ProductDTO productDTO) {
        LOGGER.info("Creating Product with productId {}", productDTO.getProductId());
        return productRepository.save(getProductEntityFromDTO(productDTO));
    }

//    public Product getProduct(String productId)
//    {
//        LOGGER.info("Get Product by productId {}", productId);
//        return productRepository.findById(productId).orElseThrow(()->new SystemException("400","Invalid ProductId"));
//
//    }
public ProductDTO getProduct(String productId)
{
    //Need to return DTO to get information of entity on Ui hence changing
    LOGGER.info("Get Product by productId {}", productId);
    Product product= productRepository.findById(productId).orElseThrow(()->new SystemException("400","Invalid ProductId"));
   return ProductDTO.builder().productId(product.getProductId()).sku(product.getSku())
           .price(product.getPrice()).title(product.getTitle()).description(product.getDescription())
           .build();
}

    private Product getProductEntityFromDTO(ProductDTO productDTO) {
        Product product = Product.builder().productId(productDTO.getProductId())
                .sku(productDTO.getSku())
                .title(productDTO.getTitle())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .createdAt(LocalDate.now())
                .orderItems(null).build();
        return product;
    }
}
