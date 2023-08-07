package com.dhbinh.yummybites.base.security.repository;

import com.dhbinh.yummybites.base.security.entity.UserRoleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, Long> {
}
