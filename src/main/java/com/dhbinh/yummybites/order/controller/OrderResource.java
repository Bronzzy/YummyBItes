package com.dhbinh.yummybites.order.controller;

import com.dhbinh.yummybites.order.service.OrderService;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@PreAuthorize("hasAnyRole('OWNER','WAITER')")
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> checkout(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.checkout(id));
    }

    @PostMapping("/export-order-by-date")
    public void exportOrderByDate(){
        orderService.exportOrderByDate();
    }
}
