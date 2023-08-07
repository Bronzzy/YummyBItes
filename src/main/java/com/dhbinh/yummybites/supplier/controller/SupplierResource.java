package com.dhbinh.yummybites.supplier.controller;

import com.dhbinh.yummybites.supplier.service.SupplierService;
import com.dhbinh.yummybites.supplier.service.dto.SupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping(value = "/suppliers")
public class SupplierResource {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO dto = supplierService.create(supplierDTO);
        return ResponseEntity.created(URI.create("/api/suppliers" + dto.getID())).body(dto);
    }
}
