package com.dhbinh.restaurantservice.base.security.repository;

import com.dhbinh.restaurantservice.base.security.entity.UserRoleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, Long> {
}
