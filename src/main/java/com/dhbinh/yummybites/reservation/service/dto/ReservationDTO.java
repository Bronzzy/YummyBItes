package com.dhbinh.yummybites.reservation.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.utils.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class ReservationDTO {

    private Long id;

    @Future(message = ErrorMessage.RESERVATION_DATE_MUST_BE_IN_FUTURE)
    @NotNull(message = ErrorMessage.RESERVATION_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationDate;

    @NotBlank(message = ErrorMessage.NAME_NULL_OR_BLANK)
    private String name;

    @NotBlank(message = ErrorMessage.EMAIL_NULL_OR_BLANK)
    @Pattern(regexp = CommonConstant.EMAIL_PATTERN, message = ErrorMessage.EMAIL_WRONG_FORMAT)
    private String email;

    @Min(value = 1, message = ErrorMessage.GUESTS_NUMBER_LESS_THAN_ONE)
    private Integer numberOfGuests;

    private String note;

    private boolean verified;
}
