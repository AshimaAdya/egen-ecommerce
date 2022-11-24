package com.order.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
    @NonNull private String productId;
    @NonNull private String sku;
    @NonNull private String title;
    @NonNull private String description;
    @NonNull private double price;
}
