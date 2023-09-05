package com.dhbinh.restaurantservice.base.security.service;


import com.dhbinh.restaurantservice.base.exception.ErrorMessage;
import com.dhbinh.restaurantservice.base.exception.InputValidationException;
import com.dhbinh.restaurantservice.base.security.entity.Role;
import com.dhbinh.restaurantservice.base.security.entity.User;
import com.dhbinh.restaurantservice.base.security.entity.UserRoleAssignment;
import com.dhbinh.restaurantservice.base.security.repository.UserRepository;
import com.dhbinh.restaurantservice.base.security.repository.UserRoleAssignmentRepository;
import com.dhbinh.restaurantservice.base.security.service.dto.UserDTO;
import com.dhbinh.restaurantservice.base.security.service.mapper.UserMapper;
import com.dhbinh.restaurantservice.employee.service.EmployeeService;
import com.dhbinh.restaurantservice.employee.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRoleAssignmentRepository userRoleAssignmentRepository;


    public List<UserDTO> getUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public UserDTO create(UserDTO userDTO) {
        verifyAndModify(userDTO);
        User user = User.builder()
                .username(userDTO.getUsername().trim())
                .password(encoder.encode(userDTO.getPassword()))
                .employee(employeeService.findByEmail(userDTO.getEmployeeEmail()))
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
