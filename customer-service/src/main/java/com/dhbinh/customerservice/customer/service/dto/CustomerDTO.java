package com.dhbinh.customerservice.customer.service.dto;

import com.dhbinh.customerservice.customer.entity.MemberTierEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Double loyaltyPoint;
    private String memberTier;
}
