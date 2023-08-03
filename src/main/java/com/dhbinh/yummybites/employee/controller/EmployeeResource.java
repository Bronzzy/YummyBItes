package com.dhbinh.yummybites.employee.controller;

import com.dhbinh.yummybites.employee.service.EmployeeService;
import com.dhbinh.yummybites.employee.service.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO dto = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.created(URI.create("/api/employees" + dto.getID())).body(dto);
    }

    @DeleteMapping(value = "/{employee-id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable("employee-id") Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
}
