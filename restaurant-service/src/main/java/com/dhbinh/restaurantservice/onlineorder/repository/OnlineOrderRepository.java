package com.dhbinh.restaurantservice.onlineorder.repository;

import com.dhbinh.restaurantservice.onlineorder.entity.OnlineOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineOrderRepository extends JpaRepository<OnlineOrder, Long> {
}
