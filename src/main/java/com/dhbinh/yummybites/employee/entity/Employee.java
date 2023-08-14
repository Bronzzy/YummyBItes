package com.dhbinh.yummybites.employee.entity;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.utils.CommonConstant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = CommonConstant.VALID_NAME_PATTERN, message = ErrorMessage.EMPLOYEE_NAME_CONTAIN_NUMBER)
    @NotBlank(message = ErrorMessage.EMPLOYEE_FIRST_NAME_NULL_OR_BLANK)
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.EMPLOYEE_LAST_NAME_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.VALID_NAME_PATTERN, message = ErrorMessage.EMPLOYEE_NAME_CONTAIN_NUMBER)
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PHONE_NUMBER_PATTERN, message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    @NotNull(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private Restaurant restaurant;
}
