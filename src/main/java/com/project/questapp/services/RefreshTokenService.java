package com.project.questapp.services;

import com.project.questapp.entities.RefreshToken;
import com.project.questapp.entities.User;
import com.project.questapp.repos.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    private Long expireSeconds;

    private final RefreshTokenRepository refreshTokenRepository;

    public String createRefreshToken(User user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if (token == null) {
            token = new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    public boolean isRefreshExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }
}
