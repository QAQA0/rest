package com.dgsw.sns.domain.user.service;

import com.dgsw.sns.domain.user.dto.UserPasswordRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest request);
    void changePassword(UserPasswordRequest request);
}
