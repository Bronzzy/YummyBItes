package com.dhbinh.yummybites.reservation.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.reservation.entity.Reservation;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper extends BaseMapper<Reservation, ReservationDTO> {
}
