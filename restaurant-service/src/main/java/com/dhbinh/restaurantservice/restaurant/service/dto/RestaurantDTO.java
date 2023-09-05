package com.dhbinh.restaurantservice.restaurant.service.dto;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private String name;

    @NotBlank(message = ErrorMessage.ADDRESS_NULL_OR_BLANK)
    private String address;

    @NotBlank(message = ErrorMessage.PHONE_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.PHONE_NUMBER_PATTERN, message = ErrorMessage.PHONE_WRONG_FORMAT)
    private String phone;

    @NotNull(message = ErrorMessage.RESTAURANT_OPEN_HOUR_NULL_OR_BLANK)
    private LocalTime openHour;

    @NotNull(message = ErrorMessage.RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK)
    private LocalTime closingHour;
}
