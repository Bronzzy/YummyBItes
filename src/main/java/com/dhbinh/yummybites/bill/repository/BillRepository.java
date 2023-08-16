package com.dhbinh.yummybites.bill.repository;

import com.dhbinh.yummybites.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>, JpaSpecificationExecutor<Bill> {

    @Query("SELECT b " +
            "FROM Bill b " +
            "WHERE FUNCTION('DAY', b.createdDate) = :day")
    List<Bill> findAllOrderByDate(@Param("day") int day);
}
