package com.dhbinh.yummybites.order.entity;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.diningtable.entity.DiningTable;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.orderdetail.entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = ErrorMessage.EMPLOYEE_NULL_OR_BLANK)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    @NotNull(message = ErrorMessage.DINING_TABLE_NULL_OR_BLANK)
    private DiningTable diningTable;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double totalPrice;

    @Column(nullable = false)
    private Boolean isPaid;

    @CreationTimestamp
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdDate;
}
