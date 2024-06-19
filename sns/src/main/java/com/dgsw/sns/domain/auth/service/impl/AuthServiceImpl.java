package com.dgsw.sns.domain.auth.service.impl;

import com.dgsw.sns.domain.auth.dto.JwtResponse;
import com.dgsw.sns.domain.auth.dto.RefreshRequest;
import com.dgsw.sns.domain.auth.service.AuthService;
import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.jwt.JwtUtils;
import com.dgsw.sns.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse login(String email, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = ((CustomUserDetails) authenticate.getPrincipal()).getUser();

        return new JwtResponse(
                jwtProvider.generateAccessToken(email), jwtProvider.generateRefreshToken(email)
        );
    }

    @Override
    public JwtResponse refresh(RefreshRequest request) {
        Claims claims = jwtProvider.getClaims(request.getRefreshToken());
        String email = claims.getSubject();

        return new JwtResponse(jwtProvider.generateAccessToken(email), request.getRefreshToken());
    }
}
