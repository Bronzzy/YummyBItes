package com.dhbinh.yummybites.base.authenticate;

import com.dhbinh.yummybites.base.security.jwt.JwtRequest;
import com.dhbinh.yummybites.base.security.jwt.JwtResponse;
import com.dhbinh.yummybites.base.security.jwt.JwtUtils;
import com.dhbinh.yummybites.base.security.service.UserDetail;
import com.dhbinh.yummybites.base.security.service.UserServices;
import com.dhbinh.yummybites.base.security.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthControllerImpl{

    @Autowired
    private UserServices userServices;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();

        String roles = "";
        if (userDetails.getAuthority() != null) {
            roles = userDetails.getAuthority().toString();
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userServices.create(userDTO);
        return ResponseEntity.created(URI.create("/users/" + dto.getId())).body(dto);
    }
}