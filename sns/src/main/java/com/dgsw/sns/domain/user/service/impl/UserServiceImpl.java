package com.dgsw.sns.domain.user.service.impl;

import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.domain.user.dto.UserRegisterRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterResponse;
import com.dgsw.sns.domain.user.repository.UserRepository;
import com.dgsw.sns.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickName())
                .password(passwordEncoder.encode(request.getPassword()))
                .uuid(UUID.randomUUID().toString())
                .build();

        userRepository.save(user);

        return new UserRegisterResponse(request.getNickName());
    }
}
