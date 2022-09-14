package com.example.firstProject.controller;
import com.example.firstProject.dto.SignUpForm;
import com.example.firstProject.entity.SignUp;
import com.example.firstProject.repository.SignUpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@Controller
@Slf4j //로깅을 위한 어노테이션
public class SignUpController {
    @Autowired//테이블을 만들어줌
    private SignUpRepository joinRepository;

    @PostMapping("/articles/SignUpAction")//회원가입
    public String SignUpAction(SignUpForm form) {
        log.info(form.toString());

        //Dto를 내가만든Article 클래스(Entity)로 변환
        SignUp signUp = form.toEntity();
        log.info(signUp.toString());

        //리포지토리한테 엔티티를 DB에 저장하게 함
        SignUp saved = joinRepository.save(signUp);
        log.info(saved.toString());

        return "/articles/SignUpAction";
    }
}




