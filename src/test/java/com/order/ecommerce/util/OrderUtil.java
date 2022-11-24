package com.order.ecommerce.util;

import com.order.ecommerce.dto.AddressDTO;
import com.order.ecommerce.dto.OrderDTO;
import com.order.ecommerce.dto.OrderItemDTO;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.enums.PaymentMode;
import com.order.ecommerce.enums.ShippingMode;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderUtil {
    public  static OrderDTO createTestOrder() {
        List<OrderItemDTO> orderItemsDTOList=new ArrayList<>();
        OrderItemDTO orderItemDTO1=new OrderItemDTO();
        orderItemDTO1.setProductId("101");
        orderItemDTO1.setQuantity("10");
        OrderItemDTO orderItemDTO2=new OrderItemDTO();
        orderItemDTO2.setProductId("102");
        orderItemDTO2.setQuantity("10");
        orderItemsDTOList.add(orderItemDTO1);
        orderItemsDTOList.add(orderItemDTO2);
        return  OrderDTO.builder()
                .customerId("1")
                .subTotal(6.0)
                .totalAmt(10.0)
                .tax(2.0).shippingCharges(2.0)
                .title("test").shippingMode(ShippingMode.DELIVERY.name())
                .amount(10.0).paymentMode(PaymentMode.CREDIT.name())
                .billingAddress(createAddress())
                .shippingAddress(createAddress()).paymentMode(PaymentMode.CASH.name())
                .orderItems(orderItemsDTOList)
                .build();
    }


   public static AddressDTO createAddress() {
        AddressDTO addressDTO=AddressDTO.builder().address1("3755 M lane")
                .address2("Apt 311").city("Aurora").state("IL").zip("60504")
                .email("test.gmail.com").phone("1234567890").build();
        return addressDTO;
    }



//   public static Order createMockOrderResponse() {
//
//        Payment payment = new Payment();
//        Address billingAddress=new Address();
//        Address shippingAddress=new Address();
//        List<OrderItem> orderItemList=new ArrayList<>();
//        LocalDateTime dateTime = LocalDateTime.parse("2022-10-17T11:31:27.771692");
//        return Order.builder().orderId("2e99fe21-2243-4004-9640-e992bbcc5040")
//                .orderStatus(OrderStatus.PROCESSING.name()).customerId("2")
//                .subTotal(6.0).totalAmt(10.0).tax(2.0).shippingCharges(2.0).title("testProduct")
//                .shippingMode(ShippingMode.DELIVERY.name()).createdAt(dateTime).payment(payment)
//                .billingAddress(billingAddress).shippingAddress(shippingAddress).orderItems(orderItemList)
//                .build();
//    }
    public static OrderDTO createMockOrderResponse() {

        Payment payment = new Payment();
        AddressDTO billingAddress=new AddressDTO();
        AddressDTO shippingAddress=new AddressDTO();
        List<OrderItemDTO> orderItemList=new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.parse("2022-10-17T11:31:27.771692");
        return OrderDTO.builder()
                .customerId("2")
                .subTotal(6.0).totalAmt(10.0).tax(2.0).shippingCharges(2.0).title("testProduct")
                .shippingMode(ShippingMode.DELIVERY.name())
                .billingAddress(billingAddress).shippingAddress(shippingAddress).orderItems(orderItemList)
                .paymentMode(PaymentMode.CASH.name()).build();
    }


}
