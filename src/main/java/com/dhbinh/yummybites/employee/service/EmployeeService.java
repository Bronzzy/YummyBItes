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
import com.dhbinh.yummybites.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestaurantService restaurantService;

    private final EmployeeMapper employeeMapper;

    private final Utils utils;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        verify(employeeDTO);

        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .phone(employeeDTO.getPhone().trim())
                .email(employeeDTO.getEmail().trim())
                .address(employeeDTO.getAddress())
                .status(StatusEnum.STATUS_ACTIVE)
                .restaurant(restaurantService.findByNameIgnoreCase(employeeDTO.getRestaurantName().trim()))
                .build();

        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO findByID(Long ID) {
        return employeeMapper.toDTO((employeeRepository.findById(ID)).
                orElseThrow(() -> new InputValidationException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND)));
    }

    public List<EmployeeDTO> findAll() {
        return employeeMapper.toDTOList(employeeRepository.findAll());
    }

    public EmployeeDTO deleteEmployee(Long ID) {
        Employee employee = employeeRepository.findById(ID).
                orElseThrow(() -> new InputValidationException(
                        ErrorMessage.KEY_RESTAURANT_NOT_FOUND,
                        ErrorMessage.RESTAURANT_NOT_FOUND));

        employee.setStatus(StatusEnum.STATUS_INACTIVE);
        return employeeMapper.toDTO(employee);
    }

    @Scheduled(cron = " 0 11 16 * * *")
    public void exportEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employee Data");

            int rowIdx = 0;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowIdx++);

                Cell cellFirstName = row.createCell(0);
                cellFirstName.setCellValue(employee.getFirstName());

                Cell cellLastName = row.createCell(1);
                cellLastName.setCellValue(employee.getLastName());

                Cell cellAddress = row.createCell(2);
                cellAddress.setCellValue(employee.getAddress());

                Cell cellPhone = row.createCell(3);
                cellPhone.setCellValue(employee.getPhone());

                Cell cellEmail = row.createCell(4);
                cellEmail.setCellValue(employee.getEmail());

                Cell cellRestaurant = row.createCell(5);
                cellRestaurant.setCellValue(employee.getRestaurant().getName());
            }

            // Write the workbook to an Excel file
            try (FileOutputStream fileOut = new FileOutputStream("employee_data.xlsx")) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void verify(EmployeeDTO employeeDTO) {

        if (employeeDTO.getFirstName() != null) {
            employeeDTO.setFirstName(utils.capitalizeFirstWordAndAfterWhitespace(employeeDTO.getFirstName().trim()));
        }

        if (employeeDTO.getLastName() != null) {
            employeeDTO.setLastName(utils.capitalizeFirstWordAndAfterWhitespace(employeeDTO.getLastName().trim()));
        }

        if (employeeDTO.getAddress() != null) {
            employeeDTO.setAddress(utils.capitalizeFirstWordAndAfterWhitespace(employeeDTO.getAddress().trim()));
        }

        if (isEmailExisted(employeeDTO.getEmail())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_EMPLOYEE_EMAIL_ALREADY_EXISTED,
                    ErrorMessage.EMPLOYEE_EMAIL_ALREADY_EXISTED);
        }
    }

    public boolean isEmailExisted(String email) {
        return employeeRepository.findByEmail(email).isPresent();
    }

    public EmployeeDTO findByEmail(String email) {
        return employeeMapper.toDTO((employeeRepository.findByEmail(email)).
                orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.KEY_EMPLOYEE_NOT_FOUND,
                        ErrorMessage.EMPLOYEE_NOT_FOUND)));
    }
}
