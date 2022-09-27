package com.example.firstProject.dto;

import com.example.firstProject.entity.Role;
import com.example.firstProject.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    /* Entity -> Dto */

    private String modifiedDate;//회원정보 수정 시 수정 날짜를 업데이트

    public UserSessionDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.modifiedDate = user.getModifiedDate();
    }
}
