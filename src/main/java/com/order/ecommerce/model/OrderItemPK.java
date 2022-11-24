package com.order.ecommerce.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class OrderItemPK implements Serializable {
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(name = "order_id", nullable = false)
    private String orderId;

}
