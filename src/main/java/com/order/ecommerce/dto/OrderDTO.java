package com.order.ecommerce.dto;

import com.order.ecommerce.enums.PaymentMode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.PrimitiveIterator;

@Getter
@Setter
@Builder
public class OrderDTO {
    @NonNull
    private String customerId;
    @NonNull
    private double subTotal;
    @NonNull
    private double totalAmt;
    @NonNull
    private double tax;
    @NonNull
    private double shippingCharges;
    @NonNull
    private String title;
    @NonNull
    private String shippingMode;

    //can be renamed as paymentAmount for clarity
    @NonNull
    private double amount;
    @NonNull
   private String paymentMode;

    @NonNull
    private  AddressDTO billingAddress;
    @NonNull
    private AddressDTO shippingAddress;

    @NonNull
    private List<OrderItemDTO> orderItems;

    private String orderStatus;
}
