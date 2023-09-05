package com.dhbinh.restaurantservice.base.security.service.dto;

import com.dhbinh.restaurantservice.base.enumvalidate.ValueOfEnum;
import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.base.security.entity.Role;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String username;

    @NotBlank(message = ErrorMessage.PASSWORD_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PASSWORD_PATTERN, message = ErrorMessage.PASSWORD_NOT_MATCH_PATTERN)
    private String password;

    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String employeeEmail;

    @NotNull(message = ErrorMessage.ENUM_NULL_OR_BLANK)
    @ValueOfEnum(enumClass = Role.class)
    @Enumerated(EnumType.STRING)
    private String role;
}
