package com.dhbinh.restaurantservice.orderdetail.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.menuitem.entity.MenuItem;
import com.dhbinh.restaurantservice.onlineorder.entity.OnlineOrder;
import com.dhbinh.restaurantservice.order.entity.Order;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    @NotNull(message = ErrorMessage.MENU_ITEM_NULL_OR_BLANK)
    private MenuItem menuItem;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.QUANTITY_LESS_THAN_ONE)
    private Integer quantity;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull(message = ErrorMessage.ORDER_NULL_OR_BLANK)
    private Order order;
}
