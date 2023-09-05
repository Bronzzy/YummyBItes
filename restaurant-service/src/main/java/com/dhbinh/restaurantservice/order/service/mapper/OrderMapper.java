package com.dhbinh.restaurantservice.order.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.order.entity.Order;
import com.dhbinh.restaurantservice.order.service.dto.OrderDTO;
import com.dhbinh.restaurantservice.orderdetail.service.mapper.OrderDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = OrderDetailMapper.class)
public interface OrderMapper extends BaseMapper<Order, OrderDTO> {

    @Mapping(target = "employeeName", source = "employee.lastName")
    @Mapping(target = "diningTableNumber", source = "diningTable.number")
    OrderDTO toDTO(Order order);
}
