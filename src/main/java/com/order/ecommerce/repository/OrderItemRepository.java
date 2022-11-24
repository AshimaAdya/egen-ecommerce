package com.order.ecommerce.repository;

import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPK> {
}
