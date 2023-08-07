package com.dhbinh.yummybites.order.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.order.entity.Order;
import com.dhbinh.yummybites.order.service.dto.OrderDTO;
import com.dhbinh.yummybites.orderdetail.service.mapper.OrderDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = OrderDetailMapper.class)
public interface OrderMapper extends BaseMapper<Order, OrderDTO> {

    @Mapping(target = "employeeName", source = "employee.lastName")
//    @Mapping(target = "orderDetails.menuItem",source = "orderDetails.menuItem.name")
    OrderDTO toDTO(Order order);
}
