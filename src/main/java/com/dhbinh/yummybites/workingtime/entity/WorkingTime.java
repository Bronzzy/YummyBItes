package com.dhbinh.yummybites.workingtime.entity;


import com.dhbinh.yummybites.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "working_time")
public class WorkingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkinHour;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutHour;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
