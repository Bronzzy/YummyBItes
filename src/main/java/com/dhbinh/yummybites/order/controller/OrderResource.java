package com.dhbinh.yummybites.order.controller;

import com.dhbinh.yummybites.order.service.OrderService;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@PreAuthorize("hasAnyRole('OWNER','WAITER')")
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Scheduled(cron = "00 22 18 * * *")
    public ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping(value = "/find-with-specification")
    public ResponseEntity<List<OrderDTO>> findWithSpecifications(@RequestParam("employeeName") String employeeName,
                                                                @RequestParam(value = "tableNumber", defaultValue = "0") int tableNumber,
                                                                @RequestParam(value = "priceLessThan",defaultValue = "0") double priceLessThan,
                                                                @RequestParam(value = "priceGreaterThan",defaultValue = "0") double priceGreaterThan,
                                                                @RequestParam(value = "day",defaultValue = "0") int day,
                                                                @RequestParam(value = "month",defaultValue = "0") int month,
                                                                @RequestParam(value = "year",defaultValue = "0") int year) {
        return ResponseEntity.ok(orderService.findWithSpecifications(employeeName, tableNumber, priceLessThan, priceGreaterThan, day, month, year));
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> checkout(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.checkout(id));
    }

    @PostMapping("/export-order-by-date")
    public void exportOrderByDate() {
        orderService.exportOrderByDate();
    }
}

