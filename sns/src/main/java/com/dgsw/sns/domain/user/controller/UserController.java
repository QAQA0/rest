package com.dgsw.sns.domain.user.controller;

import com.dgsw.sns.domain.user.dto.UserRegisterRequest;
import com.dgsw.sns.domain.user.dto.UserRegisterResponse;
import com.dgsw.sns.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserRegisterResponse> create(@RequestBody UserRegisterRequest request) {
        log.info("Create user: {}", request);
        return ResponseEntity.ok(userService.register(request));
    }

}
