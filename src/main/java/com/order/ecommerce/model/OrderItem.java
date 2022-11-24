package com.order.ecommerce.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ecommerce_order_item")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class OrderItem implements Serializable {
    @EmbeddedId
    private OrderItemPK orderItemPk;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private String quantity;
}
