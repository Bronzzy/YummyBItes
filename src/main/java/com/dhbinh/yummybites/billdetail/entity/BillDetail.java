package com.dhbinh.yummybites.billdetail.entity;

import com.dhbinh.yummybites.bill.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ingredient;

    @Column
    private Double quantity;

    @Column
    private Double pricePerUnit;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

}
