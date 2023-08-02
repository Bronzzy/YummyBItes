package com.dhbinh.yummybites.employee.service.mapper;

import com.dhbinh.yummybites.base.service.mapper.BaseMapper;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.employee.service.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {
    @Mapping(target = "restaurantName", source = "restaurant.name")
    EmployeeDTO toDTO(Employee employee);
}
