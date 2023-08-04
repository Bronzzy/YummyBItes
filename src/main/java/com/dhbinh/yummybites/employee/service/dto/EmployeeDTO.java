package com.dhbinh.yummybites.employee.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.employee.entity.RoleEnum;
import com.dhbinh.yummybites.employee.entity.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long ID;

    @NotBlank(message = ErrorMessage.EMPLOYEE_FIRST_NAME_NULL_OR_BLANK)
    @Pattern(regexp = "^[^0-9]*$", message = ErrorMessage.EMPLOYEE_NAME_CONTAIN_NUMBER)
    private String firstName;

    @NotBlank(message = ErrorMessage.EMPLOYEE_LAST_NAME_NULL_OR_BLANK)
    @Pattern(regexp = "^[^0-9]*$", message = ErrorMessage.EMPLOYEE_NAME_CONTAIN_NUMBER)
    private String lastName;

    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = "^[0-9]+$", message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @Min(value = 20000, message = ErrorMessage.EMPLOYEE_BASE_SALARY_LESS_THAN_20000)
    private Double baseSalary;

    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;

    @NotNull(message = ErrorMessage.ENUM_NULL_OR_BLANK)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @NotBlank(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private String restaurantName;
}
