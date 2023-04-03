package com.project.questapp.responses;

import com.project.questapp.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private int avatar;
    private String userName;


    public UserResponse(User entity) {
        this.id = entity.getId();
        this.avatar = entity.getAvatar();
        this.userName = entity.getUserName();
    }
}
