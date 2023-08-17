package com.dhbinh.yummybites.base.security.service;


import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.InputValidationException;
import com.dhbinh.yummybites.base.security.entity.Role;
import com.dhbinh.yummybites.base.security.entity.User;
import com.dhbinh.yummybites.base.security.entity.UserRoleAssignment;
import com.dhbinh.yummybites.base.security.repository.UserRepository;
import com.dhbinh.yummybites.base.security.repository.UserRoleAssignmentRepository;
import com.dhbinh.yummybites.base.security.service.dto.UserDTO;
import com.dhbinh.yummybites.base.security.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    private final UserRoleAssignmentRepository userRoleAssignmentRepository;

    public List<UserDTO> getUsers() {
        return userMapper.toDTOList(userRepository.findAll());

    }

    public UserDTO create(UserDTO userDTO) {
        verifyAndModify(userDTO);
        User user = User.builder()
                .username(userDTO.getUsername().trim())
                .password(encoder.encode(userDTO.getPassword()))
                .active(true)
                .build();
        userRepository.save(user);

        UserRoleAssignment assignment = new UserRoleAssignment();
        assignment.setRole(Role.valueOf(userDTO.getRole()));
        assignment.setUsers(user);
        userRoleAssignmentRepository.save(assignment);

        return userMapper.toDTO(user);
    }

    private void verifyAndModify(UserDTO userDTO) {
        if (isUserExist(userDTO.getUsername())) {
            throw new InputValidationException(
                    ErrorMessage.KEY_USERNAME_EXIST,
                    ErrorMessage.USERNAME_EXIST);
        }
    }

    private boolean isUserExist(String user) {
        return userRepository.findByUsername(user).isPresent();
    }
}
