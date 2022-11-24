package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.AddressDTO;
import com.order.ecommerce.dto.OrderItemDTO;
import com.order.ecommerce.enums.PaymentStatus;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPK;
import com.order.ecommerce.model.Payment;
import com.order.ecommerce.repository.AddressRepository;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDetailsMapper {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment buildAndLoadPayment(double amount, String paymentMode) {
        Payment payment= Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .amount(amount)
                .paymentMode(paymentMode)
                .confirmationNumber(UUID.randomUUID().toString())
                .paymentStatus(PaymentStatus.PROCESSING.name())
                .createdAt(LocalDate.now())
                .order(null)
                .build();
        paymentRepository.save(payment);
        return payment;
    }

    public Address buildAndLoadAddress(AddressDTO addressDTO) {
        Address address=getAddressEntity(addressDTO);
        return addressRepository.save(address);
    }

   public Iterable<OrderItem> buildOrderItems(
           List<OrderItemDTO>orderItemsDtoList, String orderId){
       List<OrderItem> orderItemList = orderItemsDtoList.stream().map(orderItemDTO -> new OrderItem(new OrderItemPK(orderItemDTO.getProductId(), orderId)
               , null, null, orderItemDTO.getQuantity())).collect(Collectors.toList());
       return orderItemRepository.saveAll(orderItemList);

   }

    private Address getAddressEntity(AddressDTO addressDTO)
    {
        Address address= Address.builder()
                .addressId(UUID.randomUUID().toString())
                .address1(addressDTO.getAddress1())
                .address2(addressDTO.getAddress2())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .zip(addressDTO.getZip())
                .email(addressDTO.getEmail())
                .phone(addressDTO.getPhone())
                .createdAt(LocalDate.now())
                .order(null).build();
        return address;
    }

}
