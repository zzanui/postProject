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
}



