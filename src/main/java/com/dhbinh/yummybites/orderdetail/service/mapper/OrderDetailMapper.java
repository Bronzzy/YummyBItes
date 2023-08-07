package com.dhbinh.yummybites.orderdetail.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.orderdetail.entity.OrderDetail;
import com.dhbinh.yummybites.orderdetail.service.dto.OrderDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper extends BaseMapper<OrderDetail, OrderDetailDTO> {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "menuItemName", source = "menuItem.name")
    OrderDetailDTO toDTO(OrderDetail orderDetail);
}
