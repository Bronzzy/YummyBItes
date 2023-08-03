package com.dhbinh.yummybites.restaurant.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
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

    private Long ID;

    @NotBlank(message = ErrorMessage.RESTAURANT_NAME_NULL_OR_BLANK)
    private String name;

    @NotBlank(message = ErrorMessage.RESTAURANT_ADDRESS_NULL_OR_BLANK)
    private String address;

    @NotBlank(message = ErrorMessage.RESTAURANT_PHONE_NULL_OR_BLANK)
    @Pattern(regexp = "^[0-9]*$", message = ErrorMessage.RESTAURANT_PHONE_WRONG_FORMAT)
    private String phone;

    @NotNull
    private LocalTime openHour;

    @NotNull
    private LocalTime closingHour;
}
