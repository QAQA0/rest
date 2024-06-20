package com.dgsw.sns.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String email;
    private String password;
    private String nickname;
    private String role;
}
