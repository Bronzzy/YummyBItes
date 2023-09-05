package com.dhbinh.restaurantservice.restaurant.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PHONE_NUMBER_PATTERN, message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @Column(nullable = false)
    @NotNull(message = ErrorMessage.RESTAURANT_OPEN_HOUR_NULL_OR_BLANK)
    private LocalTime openHour;

    @Column(nullable = false)
    @NotNull(message = ErrorMessage.RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK)
    private LocalTime closingHour;

}
