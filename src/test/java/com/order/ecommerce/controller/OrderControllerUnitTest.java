package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDTO;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.service.OrderService;
import com.order.ecommerce.util.OrderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class OrderControllerUnitTest {
    @Mock
    OrderService orderService;
@InjectMocks
OrderController orderController;

    private OrderDTO orderDtoRequest = OrderUtil.createTestOrder();
    private OrderCreateResponse mockOrderCreateResponse = new OrderCreateResponse("2e99fe21-2243-4004-9640-e992bbcc5040", OrderStatus.PROCESSING.name());
    //private Order mockOrderGetResponse = OrderUtil.createMockOrderResponse();
    private OrderDTO mockOrderGetResponse = OrderUtil.createMockOrderResponse();


    @Test
    void testCreateOrder() {
        Mockito.when(orderService.createOrder(orderDtoRequest))
                .thenReturn(mockOrderCreateResponse);
        OrderCreateResponse actualResponse = orderController.createOrder(orderDtoRequest);
        assertThat(actualResponse).isEqualTo(mockOrderCreateResponse);
    }

    @Test
    void testGetOrder() {
        Mockito.when(orderService.findOrderById("2e99fe21-2243-4004-9640-e992bbcc5040"))
                .thenReturn(mockOrderGetResponse);
        OrderDTO actualResponse = orderController.findOrderById("2e99fe21-2243-4004-9640-e992bbcc5040");
        assertThat(actualResponse).isEqualTo(mockOrderGetResponse);
    }
}
