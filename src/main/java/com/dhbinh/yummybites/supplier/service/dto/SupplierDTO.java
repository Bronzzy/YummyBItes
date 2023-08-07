package com.dhbinh.yummybites.supplier.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private Long ID;

    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = "^[0-9]+$", message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;
}
