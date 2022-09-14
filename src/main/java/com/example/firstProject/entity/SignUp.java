package com.example.firstProject.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Getter
@Builder
public class SignUp {
    @Id
    private String id;
    @Column
    private String password;
    @Column
    private String name;


}
