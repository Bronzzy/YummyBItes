package com.dhbinh.yummybites.reservation.controller;

import com.dhbinh.yummybites.reservation.service.ReservationService;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('OWNER','WAITER')")
@RequestMapping("/reservations")
public class ReservationResource {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@Valid @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ReservationDTO reservation) {
        ReservationDTO dto = reservationService.save(reservation);
        return ResponseEntity.created(URI.create("/api/reservations" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping(value = "/export-today-reservation")
    public void exportTodayReservation() {
        reservationService.exportReservationTodayToExcel();
    }
}
