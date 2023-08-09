package com.dhbinh.yummybites.bill.controller;

import com.dhbinh.yummybites.bill.service.BillService;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/bills")
@PreAuthorize("hasRole('OWNER')")
public class BillResource {

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<List<BillDTO>> findAll() {
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BillDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.findById(id));
    }
}
