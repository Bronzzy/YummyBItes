package com.dhbinh.restaurantservice.supplier.controller;

import com.dhbinh.restaurantservice.supplier.entity.Supplier;
import com.dhbinh.restaurantservice.supplier.service.SupplierService;
import com.dhbinh.restaurantservice.supplier.service.dto.SupplierDTO;
import com.dhbinh.restaurantservice.supplier.service.mapper.SupplierMapper;
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

    @Autowired
    private SupplierMapper supplierMapper;

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierService.create(supplierDTO);
        SupplierDTO dto = supplierMapper.toDTO(supplier);
        return ResponseEntity.created(URI.create("/api/suppliers" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findAll() {
        return ResponseEntity.ok().body(supplierMapper.toDTOList(supplierService.findAll()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(supplierMapper.toDTO(supplierService.findById(id)));
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable("id") Long id,
                                              @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierService.update(id, supplierDTO);
        SupplierDTO dto = supplierMapper.toDTO(supplier);
        return ResponseEntity.ok().body(dto);
    }
}
