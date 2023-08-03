package com.dhbinh.yummybites.workingtime.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingTimeDTO {

    private Long ID;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkinHour;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutHour;

    private Long employeeID;
}
