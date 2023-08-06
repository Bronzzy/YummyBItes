package com.dhbinh.yummybites.employee.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.employee.entity.StatusEnum;
import com.dhbinh.yummybites.employee.repository.EmployeeRepository;
import com.dhbinh.yummybites.employee.service.dto.EmployeeDTO;
import com.dhbinh.yummybites.employee.service.mapper.EmployeeMapper;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestaurantService restaurantService;

    private final EmployeeMapper employeeMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .phone(employeeDTO.getPhone())
                .baseSalary(employeeDTO.getBaseSalary() == null ? 20000 : employeeDTO.getBaseSalary())
                .email(employeeDTO.getEmail())
                .address(employeeDTO.getAddress())
                .status(StatusEnum.STATUS_ACTIVE)
                .restaurant(restaurantService.findByName(employeeDTO.getRestaurantName()))
                .build();

        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO deleteEmployee(Long ID) {
        Employee employee = employeeRepository.findById(ID).
                orElseThrow(() -> new InputValidationException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND, ErrorMessage.RESTAURANT_NOT_FOUND));

        employee.setStatus(StatusEnum.STATUS_INACTIVE);
        return employeeMapper.toDTO(employee);
    }

    public EmployeeDTO findByID(Long ID){
        return employeeMapper.toDTO((employeeRepository.findById(ID)).
                orElseThrow(() -> new InputValidationException
                        (ErrorMessage.KEY_RESTAURANT_NOT_FOUND, ErrorMessage.RESTAURANT_NOT_FOUND)));
    }

}
