package com.dgsw.sns.domain.user.dto;

import lombok.Getter;

@Getter
public class UserPasswordRequest {
    private String originPassword;
    private String password;
}
