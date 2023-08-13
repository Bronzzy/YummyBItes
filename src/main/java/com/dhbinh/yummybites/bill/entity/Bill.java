package com.dhbinh.yummybites.bill.entity;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.billdetail.entity.BillDetail;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.supplier.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @NotNull(message = ErrorMessage..)
    private Supplier supplier;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.PERSIST)
    private List<BillDetail> billDetails;

    @Column(nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdDate;
}
