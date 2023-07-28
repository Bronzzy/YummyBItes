package com.dhbinh.yummybites.restaurant.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDTO {

    private String name;

    private String address;

    private String phone;

    private LocalTime openHour;

    private LocalTime closingHour;
}
