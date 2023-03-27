package com.project.questapp.controllers;

import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.responses.PostResponse;
import com.project.questapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {

        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {

        return postService.createOnePost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public Post getOnePostById(@PathVariable Long postId) {

        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {

        return postService.updateOnePost(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {

        postService.deleteOnePost(postId);
    }
}
