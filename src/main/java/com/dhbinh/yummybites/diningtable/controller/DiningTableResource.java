package com.dhbinh.yummybites.diningtable.controller;

import com.dhbinh.yummybites.diningtable.service.DiningTableService;
import com.dhbinh.yummybites.diningtable.service.dto.DiningTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@PreAuthorize("hasRole('OWNER')")
@RequestMapping(value = "/diningtables")
@Validated
public class DiningTableResource {

    @Autowired
    private DiningTableService diningTableService;

    @PostMapping
    public ResponseEntity<DiningTableDTO> create(@Valid @RequestBody DiningTableDTO diningTableDTO) {
        DiningTableDTO dto = diningTableService.create(diningTableDTO);
        return ResponseEntity.created(URI.create("/api/tables" + dto.getId())).body(dto);
    }
}
