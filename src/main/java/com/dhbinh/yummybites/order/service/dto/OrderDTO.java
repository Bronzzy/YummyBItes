package com.dhbinh.yummybites.order.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.orderdetail.service.dto.OrderDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    @NotEmpty(message = ErrorMessage.ORDER_DETAILS_NULL_OR_BLANK)
    private List<OrderDetailDTO> orderDetails;

    @Min(value = 1, message = ErrorMessage.DINING_TABLE_MIN_NUMBER)
    private Integer diningTableNumber;

    @NotBlank(message = ErrorMessage.EMPLOYEE_LAST_NAME_NULL_OR_BLANK)
    private String employeeName;

    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double totalPrice;

    private Boolean isPaid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdDate;
}
