package com.dhbinh.yummybites.supplier.controller;

import com.dhbinh.yummybites.supplier.service.SupplierService;
import com.dhbinh.yummybites.supplier.service.dto.SupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/suppliers")
@PreAuthorize("hasAnyRole('OWNER','COOK')")
public class SupplierResource {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO dto = supplierService.create(supplierDTO);
        return ResponseEntity.created(URI.create("/api/suppliers" + dto.getId())).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(supplierService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findAll() {
        return ResponseEntity.ok().body(supplierService.findAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable("id") Long id,
                                              @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO dto = supplierService.update(id, supplierDTO);
        return ResponseEntity.ok().body(dto);
    }
}
