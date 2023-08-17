package com.dhbinh.yummybites.employee.repository;

import com.dhbinh.yummybites.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmail(String email);

    @Query("SELECT e " +
            "FROM Employee e LEFT JOIN FETCH e.restaurant")
    List<Employee> findAll();
}
