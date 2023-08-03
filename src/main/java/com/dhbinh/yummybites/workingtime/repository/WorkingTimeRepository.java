package com.dhbinh.yummybites.workingtime.repository;

import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.workingtime.entity.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Long> {

    WorkingTime findByEmployee(Employee employee);
}
