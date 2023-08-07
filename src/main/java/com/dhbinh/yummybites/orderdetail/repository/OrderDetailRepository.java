package com.dhbinh.yummybites.orderdetail.repository;

import com.dhbinh.yummybites.orderdetail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
