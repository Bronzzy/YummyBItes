package com.dhbinh.yummybites.reservation.repository;

import com.dhbinh.yummybites.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE FUNCTION('DAY', r.reservationDate) = :day ")
    List<Reservation> findByReservationDate(@Param("day") int day);
}
