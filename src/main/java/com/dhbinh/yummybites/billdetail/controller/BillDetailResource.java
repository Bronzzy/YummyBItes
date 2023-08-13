package com.dhbinh.yummybites.billdetail.controller;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import com.dhbinh.yummybites.billdetail.service.BillDetailService;
import com.dhbinh.yummybites.billdetail.service.dto.BillDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/billdetails")
@PreAuthorize("hasAnyRole('OWNER', 'COOK')")
public class BillDetailResource {

    @Autowired
    private BillDetailService billDetailService;

    @PostMapping
    public ResponseEntity<BillDTO> create(@RequestHeader("Authorization") String token,
                                          @Valid @RequestBody List<BillDetailDTO> billDetailDTOList,
                                          @RequestParam("supplierName") String supplierName) {
        BillDTO dto = billDetailService.create(token, billDetailDTOList, supplierName);
        return ResponseEntity.created(URI.create("/api/bills" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<BillDetailDTO>> findAll() {
        return ResponseEntity.ok(billDetailService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BillDetailDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(billDetailService.findById(id));
    }
}
