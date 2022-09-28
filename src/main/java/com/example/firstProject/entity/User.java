package com.example.firstProject.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* nickname과 password만 수정 가능 */
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트하고     * 기존 데이터는 그대로 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

}



