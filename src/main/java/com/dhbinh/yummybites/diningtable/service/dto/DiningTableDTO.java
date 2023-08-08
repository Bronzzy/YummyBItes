package com.dhbinh.yummybites.diningtable.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiningTableDTO {

    private Long id;

    private Integer number;

    private boolean isOccupied;
}
