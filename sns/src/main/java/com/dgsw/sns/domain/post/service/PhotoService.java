package com.dgsw.sns.domain.post.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    void uploadPhoto(int postId, List<MultipartFile> files);
}
