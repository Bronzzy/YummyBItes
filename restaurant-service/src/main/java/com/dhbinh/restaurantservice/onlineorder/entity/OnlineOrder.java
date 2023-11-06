package com.dhbinh.restaurantservice.onlineorder.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.onlineorderdetail.entity.OnlineOrderDetail;
import com.dhbinh.restaurantservice.orderdetail.entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class OnlineOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "onlineOrder",cascade = CascadeType.PERSIST)
    private List<OnlineOrderDetail> onlineOrderDetails;

    @Column
    private String destination;

    @Column
    private Double deliveryFee;

    @Column
    private String customerPhone;

    @Column
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double totalPrice;

    @Column
    private Boolean isPaid;

    @CreationTimestamp
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdDate;
}
