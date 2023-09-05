package com.dhbinh.restaurantservice.employee.controller;

import com.dhbinh.restaurantservice.employee.entity.Employee;
import com.dhbinh.restaurantservice.employee.service.EmployeeService;
import com.dhbinh.restaurantservice.employee.service.dto.EmployeeDTO;
import com.dhbinh.restaurantservice.employee.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/employees")
@PreAuthorize("hasRole('OWNER')")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;


    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee result = employeeService.createEmployee(employeeDTO);
        EmployeeDTO dto = employeeMapper.toDTO(result);
        return ResponseEntity.created(URI.create("/api/employees" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok(employeeMapper.toDTOList(employeeService.findAll()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> findByID(@PathVariable("id") Long ID) {
        return ResponseEntity.ok(employeeMapper.toDTO(employeeService.findById(ID)));
    }

    @GetMapping(value = "/find-with-specification")
    public ResponseEntity<List<EmployeeDTO>> findWithSpecifications(@RequestParam("firstName") String firstName,
                                                                    @RequestParam("lastName") String lastName,
                                                                    @RequestParam("address") String address,
                                                                    @RequestParam(value = "day", defaultValue = "0") int day,
                                                                    @RequestParam(value = "month", defaultValue = "0") int month,
                                                                    @RequestParam(value = "year", defaultValue = "0") int year,
                                                                    @RequestParam(value = "ageLessThan", defaultValue = "0") int ageLessThan,
                                                                    @RequestParam(value = "ageGreaterThan", defaultValue = "0") int ageGreaterThan) {
        return ResponseEntity.ok(employeeService.findWithSpecifications(firstName, lastName, address, day, month, year, ageLessThan, ageGreaterThan));
    }

    @PostMapping(value = "/export-employee-list")
    public void exportEmployeeList() {
        employeeService.exportEmployeeList();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
