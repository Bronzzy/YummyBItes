package com.dhbinh.restaurantservice.onlineorderdetail.entity;

import com.dhbinh.restaurantservice.menuitem.entity.MenuItem;
import com.dhbinh.restaurantservice.onlineorder.entity.OnlineOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class OnlineOrderDetail {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "online_order_id")
    private OnlineOrder onlineOrder;
}
