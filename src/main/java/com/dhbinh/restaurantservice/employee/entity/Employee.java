package com.dhbinh.restaurantservice.employee.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.restaurant.entity.Restaurant;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

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
    @NotNull(message = ErrorMessage.DATE_OF_BIRTH_NULL_OR_BLANK)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

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
