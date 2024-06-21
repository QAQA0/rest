package com.dgsw.sns.domain.post.controller;

import com.dgsw.sns.domain.post.dto.PageableRequest;
import com.dgsw.sns.domain.post.dto.PostCreateRequest;
import com.dgsw.sns.domain.post.dto.PostDTO;
import com.dgsw.sns.domain.post.dto.PostUpdateRequest;
import com.dgsw.sns.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity post(@RequestBody PostCreateRequest post) {
        postService.create(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> findAll() {
        PageableRequest pageableRequest = new PageableRequest();
        return ResponseEntity.ok(postService.getPosts(pageableRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping
    public ResponseEntity<PostDTO> update(@RequestBody PostUpdateRequest post) {
        return ResponseEntity.ok(postService.update(post));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        postService.delete(id);
    }
}
