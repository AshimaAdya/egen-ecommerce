package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDTO;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/orders")
    @Operation(summary = "Create an order", description = "Create an order")
    public OrderCreateResponse createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
    @GetMapping("/orders/{orderId}")
    @Operation(summary = "Get an order by orderId", description = "Get an order by orderId")
    public OrderDTO findOrderById(@PathVariable("orderId") String orderId) {
        return orderService.findOrderById(orderId);
    }


    @PatchMapping("/orders/{orderId}")
    @Operation(summary = "Update Order status", description = "Update Order Status")
    public void updateOrderStatus(
            @PathVariable("orderId") String orderId,
            @RequestParam("orderStatus") String orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
    }
}
