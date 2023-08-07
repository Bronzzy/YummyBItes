package com.dhbinh.yummybites.table.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.table.entity.Tables;
import com.dhbinh.yummybites.table.service.dto.TablesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TableMapper extends BaseMapper<Tables, TablesDTO> {
}
