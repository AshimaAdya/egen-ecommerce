package com.order.ecommerce.dto;

import com.order.ecommerce.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderCreateResponse {

    @NonNull
    private String orderId;
    @NonNull
    private String orderStatus;
}
