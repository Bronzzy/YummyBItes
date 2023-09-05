package com.dhbinh.restaurantservice.supplier.entity;


import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PHONE_NUMBER_PATTERN, message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;
}
