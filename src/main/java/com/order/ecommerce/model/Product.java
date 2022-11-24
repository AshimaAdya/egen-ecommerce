package com.order.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ecommerce_product")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class Product implements Serializable {
    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    String productId;

    @Column(name = "sku", nullable = false)
    String sku;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "price", nullable = false)
    double price;

    @Column(name = "createdAt", nullable = false)
    LocalDate createdAt;

    @OneToMany(targetEntity = OrderItem.class, fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderItem> orderItems;
}
