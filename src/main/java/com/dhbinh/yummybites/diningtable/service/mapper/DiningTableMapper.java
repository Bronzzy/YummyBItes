package com.dhbinh.yummybites.diningtable.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.diningtable.entity.DiningTable;
import com.dhbinh.yummybites.diningtable.service.dto.DiningTableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiningTableMapper extends BaseMapper<DiningTable, DiningTableDTO> {
}
