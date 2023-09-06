package com.dhbinh.customerservice.customer.service;

import com.dhbinh.customerservice.customer.entity.Customer;
import com.dhbinh.customerservice.customer.entity.MemberTierEnum;
import com.dhbinh.customerservice.customer.repository.CustomerRepository;
import com.dhbinh.customerservice.customer.service.dto.CustomerDTO;
import com.dhbinh.customerservice.customer.service.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public Customer create(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .loyaltyPoint(customerDTO.getLoyaltyPoint() == null ? 0 : customerDTO.getLoyaltyPoint())
                .memberTier(MemberTierEnum.valueOf(customerDTO.getMemberTier()))
                .build();
        return customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findByPhone(String phone){
        return customerRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
