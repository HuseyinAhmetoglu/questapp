package com.project.questapp.services;

import com.project.questapp.entities.User;
import com.project.questapp.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }


    public User updateOneUser(Long userId, User newUser) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setAvatar(newUser.getAvatar());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }

    }

    public void deleteOneUser(Long userId) {

        userRepository.deleteById(userId);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
