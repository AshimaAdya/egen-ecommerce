package com.order.ecommerce.service;

import com.order.ecommerce.dto.AddressDTO;
import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDTO;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.exceptionhandler.SystemException;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.Payment;
import com.order.ecommerce.repository.AddressRepository;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.OrderRepository;
import com.order.ecommerce.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private static final Logger LOGGER= LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsMapper orderDetailsMapper;
   @Autowired
    OrderItemRepository orderItemRepository;

   //Added by me
   @Autowired
    PaymentRepository paymentRepository;
   @Autowired
    AddressRepository addressRepository;



    public void updateOrderStatus(String orderId, String orderStatus) {
       Order order=orderRepository.findById(orderId).orElseThrow(()->new SystemException("400","Not a valid orderID"));
       order.setOrderStatus(orderStatus);
       orderRepository.save(order);
    }

//    public Order findOrderById(String orderId) {
//        //Always return a dto - Need to map entity to dto to get all fields
//        return orderRepository.findById(orderId).orElseThrow(()->new SystemException("400","Invalid OrderId"));
//  }
    public OrderDTO findOrderById(String orderId) {
        //Always return a dto - Need to map entity to dto to get all fields
        Order order= orderRepository.findById(orderId).orElseThrow(()->new SystemException("400","Invalid OrderId"));
        Payment payment=paymentRepository.findById(order.getPayment().getPaymentId()).orElseThrow(()->new SystemException("400","Invalid PaymentId"));
        Address shippingAddress=addressRepository.findById(order.getShippingAddress().getAddressId()).orElseThrow(()->new SystemException("400","Invalid addressId"));
        Address billingAddress=addressRepository.findById(order.getBillingAddress().getAddressId()).orElseThrow(()->new SystemException("400","Invalid addressId"));
        AddressDTO shippingAddressDTO=AddressDTO.builder().address1(shippingAddress.getAddress1()).address2(shippingAddress.getAddress2())
                        .zip(shippingAddress.getZip()).phone(shippingAddress.getPhone()).city(shippingAddress.getCity())
                        .state(shippingAddress.getState()).email(shippingAddress.getEmail()).build();
        AddressDTO billingAddressDTO=AddressDTO.builder().address1(billingAddress.getAddress1()).address2(billingAddress.getAddress2())
                .zip(billingAddress.getZip()).phone(billingAddress.getPhone()).city(billingAddress.getCity())
                .state(billingAddress.getState()).email(billingAddress.getEmail()).build();
        return OrderDTO.builder().customerId(order.getCustomerId()).orderStatus(order.getOrderStatus()).subTotal(order.getSubTotal())
                .totalAmt(order.getTotalAmt()).tax(order.getTax()).shippingCharges(order.getShippingCharges())
                .title(order.getTitle()).shippingMode(order.getShippingMode()).paymentMode(payment.getPaymentMode())
                .amount(payment.getAmount()).shippingAddress(shippingAddressDTO).billingAddress(billingAddressDTO).orderItems(new ArrayList<>()).build();

    }

    @Transactional
    public OrderCreateResponse createOrder(OrderDTO orderDTO) {
        LOGGER.info("Creating Order for customer {}", orderDTO.getCustomerId());
           Order saveOrder=orderRepository.save(createOrderFromDTO(orderDTO));

       Iterable<OrderItem> orderItemList =
        orderDetailsMapper.buildOrderItems(orderDTO.getOrderItems(),saveOrder.getOrderId());
        orderItemRepository.saveAll(orderItemList);
        //Always return a dto - Need to map entity to dto
       return new OrderCreateResponse(saveOrder.getOrderId(), saveOrder.getOrderStatus());

    }

    private Order createOrderFromDTO(OrderDTO orderDTO) {
        Order order=Order.builder()
       .orderId(UUID.randomUUID().toString())
        .orderStatus(OrderStatus.PROCESSING.name())
                .customerId(orderDTO.getCustomerId())
                .subTotal(orderDTO.getSubTotal())
                .totalAmt(orderDTO.getTotalAmt())
                .tax(orderDTO.getTax())
                .shippingCharges(orderDTO.getShippingCharges())
                .title(orderDTO.getTitle())
                .shippingMode(orderDTO.getShippingMode())
                .createdAt(LocalDateTime.now())
                .shippingAddress(orderDetailsMapper.buildAndLoadAddress(orderDTO.getShippingAddress()))
                .billingAddress(orderDetailsMapper.buildAndLoadAddress(orderDTO.getBillingAddress()))
                .payment(orderDetailsMapper.buildAndLoadPayment(orderDTO.getAmount(),orderDTO.getPaymentMode()))
                .build();
        return order;
    }


}
