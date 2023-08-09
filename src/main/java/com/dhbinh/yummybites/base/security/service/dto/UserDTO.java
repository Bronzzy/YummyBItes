package com.dhbinh.yummybites.base.security.service.dto;

import com.dhbinh.yummybites.base.enumvalidate.ValueOfEnum;
import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.security.entity.Role;
import com.dhbinh.yummybites.utils.CommonConstant;
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
import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    static final String VALID_VALUE = ErrorMessage.ENUM_VALID_VALUE;

    private Long id;

    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String username;

    @NotBlank(message = ErrorMessage.PASSWORD_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PASSWORD_PATTERN, message = ErrorMessage.PASSWORD_NOT_MATCH_PATTERN)
    private String password;

    @NotNull(message = ErrorMessage.ENUM_NULL_OR_BLANK)
    @ValueOfEnum(enumClass = Role.class, message = Role.ENUM_VALID_VALUE)

    @Enumerated(EnumType.STRING)
    private String role;
}
