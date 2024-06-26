package com.dgsw.sns.domain.user.service.impl;

import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.domain.user.dto.UserPasswordRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterResponse;
import com.dgsw.sns.domain.user.repository.UserRepository;
import com.dgsw.sns.domain.user.service.UserService;
import com.dgsw.sns.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSecurity userSecurity;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return new UserRegisterResponse(request.getNickName());
    }

    @Override
    public void changePassword(UserPasswordRequest request) {
        User user = userRepository.findById(userSecurity.getUser().getEmail())
                .orElseThrow(RuntimeException::new);

        if(passwordEncoder.matches(request.getOriginPassword(), user.getPassword())) {
            userRepository.save(User.builder()
                    .email(user.getEmail())
                    .password(request.getPassword())
                    .nickname(user.getNickname())
                    .role(user.getRole())
                    .build());
        }
    }
}
