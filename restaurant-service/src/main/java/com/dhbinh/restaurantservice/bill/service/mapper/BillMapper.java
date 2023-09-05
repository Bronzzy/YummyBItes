package com.dhbinh.restaurantservice.bill.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.bill.entity.Bill;
import com.dhbinh.restaurantservice.bill.service.dto.BillDTO;
import com.dhbinh.restaurantservice.billdetail.service.mapper.BillDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = BillDetailMapper.class)
public interface BillMapper extends BaseMapper<Bill, BillDTO> {

    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "employeeLastName", source = "employee.lastName")
    BillDTO toDTO(Bill entity);
}
