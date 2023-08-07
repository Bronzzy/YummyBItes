package com.dhbinh.yummybites.base.security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class UserRoleAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User users;

    @CreationTimestamp
    @Column(name = "assigned_date")
	private LocalDateTime assignedDate;

	@UpdateTimestamp
	@Column(name = "modified_date")
	private LocalDateTime updatedDate;
}
