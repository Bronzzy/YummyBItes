package com.dhbinh.restaurantservice.diningtable.controller;

import com.dhbinh.restaurantservice.diningtable.service.DiningTableService;
import com.dhbinh.restaurantservice.diningtable.service.dto.DiningTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

@Validated
@RestController
@PreAuthorize("hasRole('OWNER')")
@RequestMapping(value = "/diningtables")
public class DiningTableResource {

    @Autowired
    private DiningTableService diningTableService;

    @PostMapping
    public ResponseEntity<DiningTableDTO> create(@Valid @RequestBody DiningTableDTO diningTableDTO) {
        DiningTableDTO dto = diningTableService.create(diningTableDTO);
        return ResponseEntity.created(URI.create("/api/tables" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<DiningTableDTO>> findAll() {
        return ResponseEntity.ok(diningTableService.findAll());
    }
}
