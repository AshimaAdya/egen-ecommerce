package com.order.ecommerce.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String zip;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String address1;
    @NonNull
    private String address2;
}
