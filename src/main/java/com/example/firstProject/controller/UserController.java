package com.example.firstProject.controller;
import com.example.firstProject.dto.UserDto;
import com.example.firstProject.entity.Role;
import com.example.firstProject.service.UserService;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/articles/join")
    public String join() { return "articles/join";}

    @PostMapping("/articles/joinProc")//회원가입
    public String joinProc(UserDto userDto) {
        userService.join(userDto);

        return "redirect:/articles/login";
    }

    @GetMapping("/articles/login")
    public String login() { return "/articles/login"; }


    @PostMapping("/articles/LoginAction")//로그인//끝까지 다 안됨
    public String SignUpAction(UserDto userDto, BindingResult bindingResult, HttpSession session)  {


        if (bindingResult.hasErrors()){
            return "/articles/login";
        }
        //아이디랑 비밀번호을 찾아 select 실행
        User loginUser = userService.loginService(userDto);


        if(loginUser == null){//계정이 존재하지 않을경우
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "/articles/login";//로그인페이지로 이동
        }

        //로그인 성공

        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
        session.setAttribute("user", userDto);
        
        return "redirect:/";//메인화면으로 이동
        }

}






