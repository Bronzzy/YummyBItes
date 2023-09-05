package com.dhbinh.restaurantservice.supplier.service.dto;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PHONE_NUMBER_PATTERN, message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;
}
