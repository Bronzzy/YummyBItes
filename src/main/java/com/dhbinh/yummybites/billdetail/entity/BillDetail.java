package com.dhbinh.yummybites.billdetail.entity;

import com.dhbinh.yummybites.bill.entity.Bill;
import com.dhbinh.yummybites.ingredients.entity.Ingredient;
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

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double pricePerUnit;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

}
