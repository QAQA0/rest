package com.dgsw.sns.domain.post.service.impl;

import com.dgsw.sns.domain.post.domain.Photo;
import com.dgsw.sns.domain.post.domain.Post;
import com.dgsw.sns.domain.post.repository.PhotoRepository;
import com.dgsw.sns.domain.post.repository.PostRepository;
import com.dgsw.sns.domain.post.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final PostRepository postRepository;

    @Override
    public void uploadPhoto(int postId, List<MultipartFile> files) {
        files.forEach(file -> {
            UUID uuid = UUID.randomUUID();

            Photo photo = Photo.builder()
                    .photoName(uuid + file.getOriginalFilename())
                    .photoPath("/src/main/resources/")
                    .photoSize(file.getSize())
                    .build();

            Post post = postRepository.findById(postId).orElse(null);

            photo.setBoard(post);
            photoRepository.save(photo);
        });
    }
}
