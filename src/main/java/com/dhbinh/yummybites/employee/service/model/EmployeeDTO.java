package com.dhbinh.yummybites.employee.service.model;

import com.dhbinh.yummybites.base.StatusRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    @Pattern(regexp = "^[^0-9]*$")
    private String firstName;

    @Pattern(regexp = "^[^0-9]*$")
    private String lastName;

    private String address;

    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @Min(value = 0)
    private double baseSalary;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private StatusRole role;
}
