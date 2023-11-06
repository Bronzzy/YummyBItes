package com.dhbinh.restaurantservice.onlineorderdetail.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.onlineorderdetail.entity.OnlineOrderDetail;
import com.dhbinh.restaurantservice.onlineorderdetail.service.dto.OnlineOrderDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OnlineOrderDetailMapper extends BaseMapper<OnlineOrderDetail, OnlineOrderDetailDTO> {

    @Mapping(target = "onlineOrderId", source = "onlineOrder.id")
    @Mapping(target = "menuItemName", source = "menuItem.name")
    OnlineOrderDetailDTO toDTO(OnlineOrderDetail onlineOrderDetail);
}
