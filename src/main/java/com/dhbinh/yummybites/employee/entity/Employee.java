package com.dhbinh.yummybites.employee.entity;

import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "^[^0-9]*$")
    private String firstName;

    @Column(nullable = false)
    @Pattern(regexp = "^[^0-9]*$")
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9-]+$")
    private String phone;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;
}
