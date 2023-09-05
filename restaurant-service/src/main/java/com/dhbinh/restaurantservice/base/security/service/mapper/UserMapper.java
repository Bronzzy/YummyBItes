package com.dhbinh.restaurantservice.base.security.service.mapper;


import com.dhbinh.restaurantservice.base.mapper.BaseMapper;
import com.dhbinh.restaurantservice.base.security.entity.User;
import com.dhbinh.restaurantservice.base.security.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDTO> {

    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);
}