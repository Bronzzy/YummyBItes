package com.dhbinh.yummybites.base.security.service;

import com.dhbinh.yummybites.base.security.entity.Role;
import com.dhbinh.yummybites.base.security.entity.User;
import com.dhbinh.yummybites.base.security.repository.UserRepository;
import com.dhbinh.yummybites.base.security.service.dto.UserDTO;
import com.dhbinh.yummybites.base.security.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;


    public List<UserDTO> getUsers() {
        return userMapper.toDTOList(userRepository.findAll());

    }

    public UserDTO create(UserDTO userDTO){
        User user = User.builder()
                .username(userDTO.getUsername().trim())
                .password(encoder.encode(userDTO.getPassword()))
                .role(Role.valueOf(userDTO.getRole()))
                .active(true)
                .build();

        return userMapper.toDTO(userRepository.save(user));
    }
}
