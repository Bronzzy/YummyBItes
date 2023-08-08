package com.dhbinh.yummybites.bill.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.bill.entity.Bill;
import com.dhbinh.yummybites.bill.service.dto.BillDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BillMapper extends BaseMapper<Bill, BillDTO> {
}
