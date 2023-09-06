package com.dhbinh.customerservice.customer.service.mapper;

import com.dhbinh.customerservice.customer.entity.Customer;
import com.dhbinh.customerservice.customer.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);
    List<CustomerDTO> toDTOs(List<Customer> customerList);
    Customer toEntity(CustomerDTO dto);
    List<Customer> toEntities(List<CustomerDTO> dtoList);

}
