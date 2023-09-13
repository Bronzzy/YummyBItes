package com.dhbinh.restaurantservice.onlineorder.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.onlineorder.entity.OnlineOrder;
import com.dhbinh.restaurantservice.onlineorder.service.dto.OnlineOrderDTO;
import com.dhbinh.restaurantservice.onlineorderdetail.service.mapper.OnlineOrderDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = OnlineOrderDetailMapper.class)
public interface OnlineOrderMapper extends BaseMapper<OnlineOrder, OnlineOrderDTO> {
}
