package com.dhbinh.yummybites.workingtime.controller;

import com.dhbinh.yummybites.workingtime.service.WorkingTimeService;
import com.dhbinh.yummybites.workingtime.service.dto.WorkingTimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/workingtimes")
public class WorkingTimeResource {

    @Autowired
    private WorkingTimeService workingTimeService;

    @PostMapping(value = "{employee-id}")
    public ResponseEntity<WorkingTimeDTO> checkin(@PathVariable("employee-id") Long employeeID){
        WorkingTimeDTO workingTimeDTO = workingTimeService.checkin(employeeID);
        return ResponseEntity.created(URI.create("/api/workingtimes" + workingTimeDTO.getID())).body(workingTimeDTO);
    }

    @PutMapping(value = "{employee-id}")
    public ResponseEntity<WorkingTimeDTO> checkout(@PathVariable("employee-id") Long employeeID){
        return ResponseEntity.ok(workingTimeService.checkout(employeeID));
    }

    @GetMapping(value = "{employee-id}")
    public ResponseEntity getWorkingTime(@PathVariable("employee-id") Long employeeID){
        return ResponseEntity.ok(workingTimeService.calculateWorkingTime(employeeID));
    }
}
