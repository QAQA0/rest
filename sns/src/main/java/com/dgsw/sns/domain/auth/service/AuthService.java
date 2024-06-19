package com.dgsw.sns.domain.auth.service;

import com.dgsw.sns.domain.auth.dto.JwtResponse;
import com.dgsw.sns.domain.auth.dto.RefreshRequest;

public interface AuthService {
    JwtResponse login(String email, String password);

    JwtResponse refresh(RefreshRequest request);
}
