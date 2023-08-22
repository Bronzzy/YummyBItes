package com.dhbinh.yummybites.base.security.config;

import com.dhbinh.yummybites.base.exception.ErrorMessage;
import com.dhbinh.yummybites.base.exception.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
//        logger.error("Unauthorized error: {}", authException.getMessage());
//
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//        ResponseBody responseBody = null;
//        if (authException instanceof InsufficientAuthenticationException) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////            body.put("status", HttpServletResponse.SC_FORBIDDEN);
////            body.put("error", "Forbidden");
//            responseBody = new ResponseBody(HttpStatus.FORBIDDEN, ErrorMessage.KEY_FORBIDDEN, authException.getMessage());
//        } else {
//            responseBody = new ResponseBody(HttpStatus.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED, authException.getMessage());
////        }
//////        body.put("message", authException.getMessage());
//////        body.put("path", request.getServletPath());
////
////
////
//            final ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//            mapper.writeValue(response.getOutputStream(), responseBody);
//        }
    }
}


