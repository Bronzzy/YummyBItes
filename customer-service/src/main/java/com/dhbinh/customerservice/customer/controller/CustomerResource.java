package com.dhbinh.customerservice.customer.controller;

import com.dhbinh.customerservice.customer.entity.Customer;
import com.dhbinh.customerservice.customer.service.CustomerService;
import com.dhbinh.customerservice.customer.service.dto.CustomerDTO;
import com.dhbinh.customerservice.customer.service.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO){
        Customer customer = customerService.create(customerDTO);
        CustomerDTO dto = customerMapper.toDTO(customer);
        return ResponseEntity.created(URI.create("/api/customer" + dto.getId())).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        CustomerDTO dto = customerMapper.toDTO(customer);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> dtos = customerMapper.toDTOs(customers);
        return ResponseEntity.ok().body(dtos);
    }
}
