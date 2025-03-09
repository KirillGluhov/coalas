package patterns.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.users.config.sequrity.JwtProvider;
import patterns.users.data.dto.response.TokenDto;
import patterns.users.data.entity.UserEntity;
import patterns.users.data.enums.Role;

@Service
@RequiredArgsConstructor
public class TokenService {

    public TokenDto getTokens(UserEntity user) {
        return new TokenDto(JwtProvider.generateAccessToken(user.getId(), user.getRole().toString()),
                JwtProvider.generateRefreshToken(user.getId()));
    }

    public String extractUserId(String token) {
        return JwtProvider.extractUserId(token);
    }
}
