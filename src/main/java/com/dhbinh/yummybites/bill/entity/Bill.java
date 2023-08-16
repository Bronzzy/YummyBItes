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

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @NotNull(message = ErrorMessage.SUPPLIER_NULL_OR_BLANK)
    private Supplier supplier;

    @NotEmpty(message = ErrorMessage.BILL_DETAIL_NULL_OR_BLANK)
    @OneToMany(mappedBy = "bill",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<BillDetail> billDetails;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    @NotNull(message = ErrorMessage.EMPLOYEE_NULL_OR_BLANK)
    private Employee employee;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdDate;
}
