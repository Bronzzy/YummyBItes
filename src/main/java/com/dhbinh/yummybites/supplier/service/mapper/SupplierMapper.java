package com.dhbinh.yummybites.supplier.service.mapper;

import com.dhbinh.yummybites.base.service.mapper.BaseMapper;
import com.dhbinh.yummybites.supplier.entity.Supplier;
import com.dhbinh.yummybites.supplier.service.dto.SupplierDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper extends BaseMapper<Supplier, SupplierDTO> {
}
