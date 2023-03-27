package com.project.questapp.services;

import com.project.questapp.entities.User;
import com.project.questapp.repos.UserRepository;
import com.project.questapp.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long userid) {
        User user = userRepository.findById(userid).get();
        return JwtUserDetails.create(user);
    }

}
