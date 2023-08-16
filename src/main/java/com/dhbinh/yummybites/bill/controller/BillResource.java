package com.dhbinh.yummybites.bill.controller;

import com.dhbinh.yummybites.bill.service.BillService;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PostMapping(value = "/export-bill-by-date")
    public void exportBillByDate() throws IOException {
        billService.exportBillByDate();
    }

//    @GetMapping(value = "/with-date-specification")
//    public ResponseEntity<List<BillDTO>> findWithDate(@RequestParam("day") String day,
//                                                      @RequestParam("month") String month,
//                                                      @RequestParam("year") String year,
//                                                      @RequestParam(value = "priceLessThan", defaultValue = "0") double priceLessThan,
//                                                      @RequestParam(value = "priceGreaterThan", defaultValue = "0") double priceGreaterThan) {
//        return ResponseEntity.ok(billService.findByDate(day, month, year, priceLessThan, priceGreaterThan));
//
//    }
}
