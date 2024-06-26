package com.dgsw.sns.domain.user.controller;

import com.dgsw.sns.domain.user.dto.UserPasswordRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterResponse;
import com.dgsw.sns.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> create(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PatchMapping
    public void changePassword(UserPasswordRequest request) {

    }

}
