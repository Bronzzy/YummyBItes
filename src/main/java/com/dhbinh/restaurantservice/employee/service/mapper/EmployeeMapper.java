package com.dhbinh.restaurantservice.employee.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.employee.entity.Employee;
import com.dhbinh.restaurantservice.employee.service.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDTO(Employee employee);
}
