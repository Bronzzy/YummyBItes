package com.dhbinh.yummybites.order.repository;

import com.dhbinh.yummybites.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    //    @Query("SELECT o " +
//            "FROM Order o " +
//            "WHERE FUNCTION('DAY', o.createdDate) = :day")
//    List<Order> findAllOrderByDate(@Param("day") int day);
    @Query("SELECT o " +
            "FROM Order o LEFT JOIN FETCH o.orderDetails " +
            "WHERE FUNCTION('DAY', o.createdDate) = :day")
    List<Order> findAllOrderByDate(@Param("day") int day);
}
