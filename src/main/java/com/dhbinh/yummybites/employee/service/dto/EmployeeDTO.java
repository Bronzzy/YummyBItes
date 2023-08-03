package com.dhbinh.yummybites.employee.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.employee.entity.RoleEnum;
import com.dhbinh.yummybites.employee.entity.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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

    @NotBlank(message = ErrorMessage.EMPLOYEE_FIRST_NAME_NULL_OR_BLANK)
    @Pattern(regexp = "^[^0-9]*$", message = ErrorMessage.EMPLOYEE_NAME_CONTAIN_NUMBER)
    private String lastName;

    @NotBlank(message = ErrorMessage.EMPLOYEE_ADDRESS_NULL_OR_BLANK)
    private String address;

    @NotBlank(message = ErrorMessage.EMPLOYEE_PHONE_NULL_OR_BLANK)
    @Pattern(regexp = "^[0-9]+$", message = ErrorMessage.EMPLOYEE_PHONE_WRONG_FORMAT)
    private String phone;

    @Min(value = 20000, message = ErrorMessage.EMPLOYEE_BASE_SALARY_LESS_THAN_20000)
    private Double baseSalary;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = "Not a valid email format")
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @NotBlank
    private String restaurantName;
}
