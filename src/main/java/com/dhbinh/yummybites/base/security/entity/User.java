package com.dhbinh.yummybites.base.security.entity;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$", message = ErrorMessage.PASSWORD_NOT_MATCH_PATTERN)
    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
    private List<UserRoleAssignment> roles = new ArrayList<>();
}
