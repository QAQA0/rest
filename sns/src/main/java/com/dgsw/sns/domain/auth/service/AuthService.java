package com.dgsw.sns.domain.auth.service;

import com.dgsw.sns.domain.auth.dto.JwtResponse;
import com.dgsw.sns.domain.auth.dto.LoginRequest;
import com.dgsw.sns.domain.auth.dto.RefreshRequest;

public interface AuthService {
    JwtResponse login(LoginRequest request);

    JwtResponse refresh(RefreshRequest request);

}
