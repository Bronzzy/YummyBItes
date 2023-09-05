package com.dhbinh.restaurantservice.diningtable.service.mapper;

import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.diningtable.entity.DiningTable;
import com.dhbinh.restaurantservice.diningtable.service.dto.DiningTableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiningTableMapper extends BaseMapper<DiningTable, DiningTableDTO> {
}
