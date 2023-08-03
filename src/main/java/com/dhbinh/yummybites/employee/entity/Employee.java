package com.dhbinh.yummybites.employee.entity;

import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;


    @Column
    @Pattern(regexp = "^[^0-9]*$")
    private String firstName;

    @Column
    @Pattern(regexp = "^[^0-9]*$")
    private String lastName;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    @Min(value = 20000)
    @Digits(fraction = 2, integer = 10)
    private Double baseSalary;

    @Column
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")
    private String email;


    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
}
