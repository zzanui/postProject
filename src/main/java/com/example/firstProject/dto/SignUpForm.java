package com.example.firstProject.dto;

import com.example.firstProject.entity.SignUp;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SignUpForm {
    private String id;
    private String password;
    private String name;


    public SignUp toEntity(){
        return new SignUp(id,password,name);

    }
}