package com.dhbinh.restaurantservice.billdetail.repository;

import com.dhbinh.restaurantservice.billdetail.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

}
