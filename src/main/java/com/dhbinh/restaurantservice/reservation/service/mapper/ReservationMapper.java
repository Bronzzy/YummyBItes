package com.dhbinh.restaurantservice.reservation.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.reservation.entity.Reservation;
import com.dhbinh.restaurantservice.reservation.service.dto.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper extends BaseMapper<Reservation, ReservationDTO> {
}
