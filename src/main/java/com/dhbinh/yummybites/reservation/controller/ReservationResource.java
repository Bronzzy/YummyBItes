package com.dhbinh.yummybites.reservation.controller;

import com.dhbinh.yummybites.reservation.entity.Reservation;
import com.dhbinh.yummybites.reservation.service.ReservationService;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@PreAuthorize("hasRole('WAITER')")
@RequestMapping("/reservations")
public class ReservationResource {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ReservationDTO reservation) {
        ReservationDTO dto = reservationService.save(reservation);
        return ResponseEntity.created(URI.create("/api/reservations" + dto.getId())).body(dto);
    }
}
