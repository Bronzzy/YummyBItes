package com.dhbinh.yummybites.base.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Date;

public class JwtUtils implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 12 * 60 * 60;

    @Value("${jwt.secret}")
    private String jwtSecret;

}
