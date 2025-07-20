package com.murathnakts.services.impl;

import com.murathnakts.dto.DtoRefreshToken;
import com.murathnakts.dto.DtoToken;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.User;
import com.murathnakts.jwt.JwtService;
import com.murathnakts.repository.RefreshTokenRepository;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Override
    public DtoUser register(DtoUser request) {
        DtoUser dtoUser = new DtoUser();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public DtoToken authenticate(DtoUser request) {
        try {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);
            Optional<User> optional = userRepository.findByUsername(request.getUsername());
            if (optional.isEmpty()) {
                return null;
            }
            User user = optional.get();
            String accessToken = jwtService.generateToken(user);
            RefreshToken refreshToken = createRefreshToken(user);
            refreshTokenRepository.save(refreshToken);
            return new DtoToken(accessToken, refreshToken.getRefreshToken());
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public DtoToken refreshToken(DtoRefreshToken refreshToken) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(refreshToken.getRefreshToken());
        if (optional.isEmpty()) {
            System.out.println("Refresh token not found: " + refreshToken);
            return null;
        }
        RefreshToken token = optional.get();
        if (!isRefreshTokenExpired(token.getExpireDate())) {
            System.out.println("Refresh token expired: " + token.getExpireDate());
            return null;
        }
        String accessToken = jwtService.generateToken(token.getUser());
        RefreshToken refreshTokenNew = createRefreshToken(token.getUser());
        refreshTokenRepository.save(refreshTokenNew);
        return new DtoToken(accessToken, refreshTokenNew.getRefreshToken());
    }

    public boolean isRefreshTokenExpired(Date expireDate) {
        return new Date().before(expireDate);
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setUser(user);
        return refreshToken;
    }
}
