package com.dhbinh.yummybites.employee.service;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
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
                .firstName(employeeDTO.getFirstName().trim())
                .lastName(employeeDTO.getLastName().trim())
                .phone(employeeDTO.getPhone().trim())
                .email(employeeDTO.getEmail().trim())
                .address(employeeDTO.getAddress().trim())
                .status(StatusEnum.STATUS_ACTIVE)
                .restaurant(restaurantService.findByNameIgnoreCase(employeeDTO.getRestaurantName().trim()))
                .build();

        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO deleteEmployee(Long ID) {
        Employee employee = employeeRepository.findById(ID).
                orElseThrow(() -> new InputValidationException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND));

        employee.setStatus(StatusEnum.STATUS_INACTIVE);
        return employeeMapper.toDTO(employee);
    }

    public EmployeeDTO findByID(Long ID){
        return employeeMapper.toDTO((employeeRepository.findById(ID)).
                orElseThrow(() -> new InputValidationException(
                                ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                                ErrorMessage.RESTAURANT_NOT_FOUND)));
    }

    public EmployeeDTO findByEmail(String email){
        return employeeMapper.toDTO((employeeRepository.findByEmail(email)).
                orElseThrow(() -> new ResourceNotFoundException(
                                ErrorMessage.KEY_EMPLOYEE_NOT_FOUND,
                                ErrorMessage.EMPLOYEE_NOT_FOUND)));
    }
}
