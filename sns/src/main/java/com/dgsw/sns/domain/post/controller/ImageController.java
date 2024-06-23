package com.dgsw.sns.domain.post.controller;

import com.dgsw.sns.domain.post.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/post/image")
@RequiredArgsConstructor
public class ImageController {

    private final PhotoService photoService;

    @PostMapping("/{postId}")
    public ResponseEntity upload(@PathVariable int postId, List<MultipartFile> files) {
        photoService.uploadPhoto(postId, files);
        return ResponseEntity.ok().build();
    }

}
