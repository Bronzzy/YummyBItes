package com.dhbinh.yummybites.billdetail.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.billdetail.entity.BillDetail;
import com.dhbinh.yummybites.billdetail.service.dto.BillDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BillDetailMapper extends BaseMapper<BillDetail, BillDetailDTO> {

    @Mapping(target = "billId", source = "bill.id")
    @Mapping(target = "ingredientName", source = "ingredient.name")
    BillDetailDTO toDTO(BillDetail entity);
}
