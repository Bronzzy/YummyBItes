package com.dhbinh.yummybites.bill.service.dto;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.billdetail.service.dto.BillDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDTO {

    private Long id;

    @NotBlank(message = ErrorMessage.SUPPLIER_NAME_NULL_OR_BLANK)
    private String supplierName;

    @NotBlank(message = ErrorMessage.BILL_DETAIL_NULL_OR_BLANK)
    private List<@NotBlank(message = ErrorMessage.BILL_DETAIL_NULL_OR_BLANK ) BillDetailDTO> billDetails;

    @Min(value = 1, message = ErrorMessage.PRICE_LESS_THAN_ONE)
    private Double totalPrice;

    @NotBlank(message = ErrorMessage.EMPLOYEE_LAST_NAME_NULL_OR_BLANK)
    private String employeeLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdDate;
}
