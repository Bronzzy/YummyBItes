package com.dhbinh.yummybites.employee.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.employee.service.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Mapping(target = "restaurantName", source = "restaurant.name")
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDTO(Employee employee);
}
