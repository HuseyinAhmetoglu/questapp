package com.project.questapp.controllers;

import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllCommentsWithParam(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {

        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest) {

        return commentService.createOneComment(commentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getOneCommentById(@PathVariable Long commentId) {

        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {

        return commentService.updateOneComment(commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {

        commentService.deleteOneComment(commentId);
    }

}
