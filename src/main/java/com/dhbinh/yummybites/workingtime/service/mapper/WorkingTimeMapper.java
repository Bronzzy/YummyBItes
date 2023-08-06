package com.dhbinh.yummybites.workingtime.service.mapper;

import com.dhbinh.yummybites.base.mapper.BaseMapper;
import com.dhbinh.yummybites.workingtime.entity.WorkingTime;
import com.dhbinh.yummybites.workingtime.service.dto.WorkingTimeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkingTimeMapper extends BaseMapper<WorkingTime, WorkingTimeDTO> {

    @Mapping(target = "employeeID", source = "employee.ID")
    WorkingTimeDTO toDTO(WorkingTime workingTime);
}
