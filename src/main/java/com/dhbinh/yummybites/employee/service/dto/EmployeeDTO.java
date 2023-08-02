package com.dhbinh.yummybites.employee.service.dto;

import com.dhbinh.yummybites.employee.entity.RoleEnum;
import com.dhbinh.yummybites.employee.entity.StatusEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    @Pattern(regexp = "^[^0-9]*$", message = "Name cannot contain number")
    private String firstName;

    @Pattern(regexp = "^[^0-9]*$", message = "Name cannot contain number")
    private String lastName;

    @Column
    private String address;

    @Column
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
    private String phone;

    @Min(value = 20000, message = "Salary must be greater than 20000")
    private Double baseSalary;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = "Not a valid email format")
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String restaurantName;
}
