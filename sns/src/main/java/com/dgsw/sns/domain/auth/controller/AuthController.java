package com.dgsw.sns.domain.auth.controller;

import com.dgsw.sns.domain.auth.dto.JwtResponse;
import com.dgsw.sns.domain.auth.dto.LoginRequest;
import com.dgsw.sns.domain.auth.dto.RefreshRequest;
import com.dgsw.sns.domain.auth.service.AuthService;
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
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        log.info("Login request: {}", request.toString());
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok().body(authService.refresh(request));
    }

}
