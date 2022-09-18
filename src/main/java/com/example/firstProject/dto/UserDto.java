package com.example.firstProject.dto;

import com.example.firstProject.entity.Role;
import com.example.firstProject.entity.User;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    /* DTO -> Entity */

    public User toEntity() {
        User user = User.builder()
                .id(null)
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .role(role.USER)
                .build();
        return user;

    }
}

