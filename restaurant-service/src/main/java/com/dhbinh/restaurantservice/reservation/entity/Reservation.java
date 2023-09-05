package com.dhbinh.restaurantservice.reservation.entity;

import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.utils.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = ErrorMessage.RESERVATION_NULL)
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationDate;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @Column(nullable = false)
    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;

    @Column(nullable = false)
    @Min(value = 1, message = ErrorMessage.GUESTS_NUMBER_LESS_THAN_ONE)
    private Integer numberOfGuests;

    @Column(nullable = false)
    private String note;

    @Column
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column
    private boolean verified;
}
