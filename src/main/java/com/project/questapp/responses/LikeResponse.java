package com.project.questapp.responses;

import com.project.questapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private Long id;
    private Long userId;
    private Long postId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.postId = like.getPost().getId();
        this.userId = like.getUser().getId();
    }
}
