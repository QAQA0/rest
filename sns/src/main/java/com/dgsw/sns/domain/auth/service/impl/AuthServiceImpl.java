package com.dgsw.sns.domain.auth.service.impl;

import com.dgsw.sns.domain.auth.dto.JwtResponse;
import com.dgsw.sns.domain.auth.dto.LoginRequest;
import com.dgsw.sns.domain.auth.dto.RefreshRequest;
import com.dgsw.sns.domain.auth.service.AuthService;
import com.dgsw.sns.domain.user.dto.UserDTO;
import com.dgsw.sns.jwt.JwtUtils;
import com.dgsw.sns.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        UserDTO user = ((CustomUserDetails) authenticate.getPrincipal()).getUser();

        return JwtResponse.builder()
                .accessToken(jwtUtils.generateAccessToken(user.getEmail()))
                .refreshToken(jwtUtils.generateRefreshToken(user.getEmail()))
                .build();
    }

    @Override
    public JwtResponse refresh(RefreshRequest request) {
        Claims claims = jwtUtils.getClaims(request.getRefreshToken());
        String email = claims.getSubject();

        return new JwtResponse(jwtUtils.generateAccessToken(email), request.getRefreshToken());
    }
}

