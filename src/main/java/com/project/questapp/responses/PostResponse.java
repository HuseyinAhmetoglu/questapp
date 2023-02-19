package com.project.questapp.responses;

import com.project.questapp.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;
    private List<LikeResponse> likes;

    public PostResponse(Post post, List<LikeResponse> likes) {
        this.id = post.getId();
        this.text = post.getText();
        this.title = post.getTitle();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUserName();
        this.likes = likes;
    }
}
