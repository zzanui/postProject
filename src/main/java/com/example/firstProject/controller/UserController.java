package com.example.firstProject.controller;
import com.example.firstProject.dto.UserDto;
import com.example.firstProject.entity.Role;
import com.example.firstProject.service.UserService;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/articles/join")
    public String join() { return "articles/join";}

    @PostMapping("/articles/joinProc")//회원가입
    public String joinProc(UserDto userDto) {

        System.out.println("1");
        System.out.println("\n"+(userDto.toString())+"\n");
        System.out.println("2");

        userService.join(userDto);


        return "redirect:/articles/login";
    }

    @GetMapping("/articles/login")
    public String login() { return "/articles/login"; }
}






