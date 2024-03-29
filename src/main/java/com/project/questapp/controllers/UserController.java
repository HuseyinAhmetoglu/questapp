package com.project.questapp.controllers;

import com.project.questapp.entities.User;
import com.project.questapp.responses.UserResponse;
import com.project.questapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {

        return userService.createUser(newUser);
    }

    @GetMapping("/{userId}")
    public UserResponse getOneUserById(@PathVariable Long userId) {
        //custom exception
        return new UserResponse(userService.getOneUserById(userId));
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {

        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {

        userService.deleteOneUser(userId);
    }

}
