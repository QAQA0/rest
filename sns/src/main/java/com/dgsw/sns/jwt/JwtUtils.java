package com.dgsw.sns.jwt;

import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.domain.user.dto.UserDTO;
import com.dgsw.sns.domain.user.repository.UserRepository;
import com.dgsw.sns.jwt.properties.JwtProperties;
import com.dgsw.sns.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    public String generateAccessToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration())) // 1 hour
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // Convert secret key to bytes
                .compact();
    }

    public String generateRefreshToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpiration())) // 10 day
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // Convert secret key to bytes
                .compact();
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey().getBytes()) // Convert secret key to bytes
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token", e);
            throw new JwtException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token", e);
            throw new JwtException("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token", e);
            throw new JwtException("Invalid JWT token");
        } catch (SignatureException e) {
            log.info("Invalid JWT signature", e);
            throw new JwtException("Invalid JWT token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
            throw new JwtException("JWT claims string is empty");
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        String email = claims.getSubject();

        User userEntity = userRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        UserDTO user = UserDTO.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .role(userEntity.getRole())
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }
}
