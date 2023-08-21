package com.dhbinh.yummybites.reservation.repository;

import com.dhbinh.yummybites.reservation.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
