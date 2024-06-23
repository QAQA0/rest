package com.dgsw.sns.domain.like.controller;

import com.dgsw.sns.domain.like.dto.LikeRegisterRequest;
import com.dgsw.sns.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity addLike(LikeRegisterRequest request) {
        likeService.addLike(request);
        return ResponseEntity.ok().build();
    }
}
