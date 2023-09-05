package com.dhbinh.restaurantservice.bill.controller;

import com.dhbinh.restaurantservice.bill.service.BillService;
import com.dhbinh.restaurantservice.bill.service.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/bills")
@PreAuthorize("hasAnyRole('OWNER','COOK')")
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

    @GetMapping(value = "/with-date-specification")
    public ResponseEntity<List<BillDTO>> findWithSpecifications(@RequestParam(value = "employeeName") String employeeName,
                                                                @RequestParam(value = "supplierName") String supplierName,
                                                                @RequestParam(value = "day", defaultValue = "0") int day,
                                                                @RequestParam(value = "month", defaultValue = "0") int month,
                                                                @RequestParam(value = "year", defaultValue = "0") int year,
                                                                @RequestParam(value = "priceLessThan", defaultValue = "0") double priceLessThan,
                                                                @RequestParam(value = "priceGreaterThan", defaultValue = "0") double priceGreaterThan) {

        return ResponseEntity.ok(billService.findWithSpecifications(employeeName, supplierName, day, month, year, priceLessThan, priceGreaterThan));
    }

    @PostMapping(value = "/export-bill-by-date")
    public void exportBillByDate() throws IOException {
        billService.exportBillByDate();
    }
}
