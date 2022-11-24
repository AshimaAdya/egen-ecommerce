package com.order.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;

@Entity
@Table(name = "ecommerce_address")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Id
    @Column(name="address_id",nullable = false,unique = true)
    private String addressId;
    @Column(name = "address1", nullable = false)
    private String address1;
    @Column(name = "address2", nullable = false)
    private String address2;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "billingAddress")
    private Order order;

}
