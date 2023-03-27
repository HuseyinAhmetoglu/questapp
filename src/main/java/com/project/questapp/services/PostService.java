package com.project.questapp.services;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.PostRepository;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.responses.LikeResponse;
import com.project.questapp.responses.PostResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeService likeService;

    public PostService(PostRepository postRepository, UserService userService, @Lazy LikeService likeService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
    }

//    public PostService(PostRepository postRepository, UserService userService) {
//        this.postRepository = postRepository;
//        this.userService = userService;
//    }
//
//    @Autowired
//    public void setLikeService(LikeService likeService) {
//        this.likeService = likeService;
//    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> {
            List<LikeResponse> likes =
                    likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);
        }).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null) return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            return postRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
