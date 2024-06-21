package com.dgsw.sns.security.service;

import com.dgsw.sns.domain.user.domain.User;
import com.dgsw.sns.domain.user.dto.UserDTO;
import com.dgsw.sns.domain.user.repository.UserRepository;
import com.dgsw.sns.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User memberEntity
                = userRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + email));

        UserDTO user = UserDTO.builder()
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .nickname(memberEntity.getNickname())
                .role(memberEntity.getRole())
                .build();

        return new CustomUserDetails(user);
    }
}
