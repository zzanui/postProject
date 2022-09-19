package com.example.firstProject.controller;

import com.example.firstProject.dto.UserDto;
import com.example.firstProject.service.UserService;
import com.example.firstProject.entity.User;
import com.example.firstProject.validate.CheckEmailValidator;
import com.example.firstProject.validate.CheckNicknameValidator;
import com.example.firstProject.validate.CheckUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private CheckUsernameValidator checkUsernameValidator;
    @Autowired
    private CheckNicknameValidator checkNicknameValidator;
    @Autowired
    private CheckEmailValidator checkEmailValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkNicknameValidator);
        binder.addValidators(checkEmailValidator);
    }


    @GetMapping("/articles/join")
    public String join() {
        return "articles/join";
    }


    @PostMapping("/articles/joinProc")//회원가입
    public String joinProc(@Valid UserDto userDto, Errors errors, Model model) {

        if (errors.hasErrors()) {
            //회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("userDto", userDto);

            //유효성을 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            //회원가입 페이지로 다시 리턴
            return "articles/join";
        }

        userService.join(userDto);
        return "redirect:/articles/login";
    }

    @GetMapping("/articles/login")
    public String login() {
        return "/articles/login";
    }


    @PostMapping("/articles/LoginAction")//로그인//끝까지 다 안됨
    public String SignUpAction(UserDto userDto, BindingResult bindingResult, HttpSession session) {


        if (bindingResult.hasErrors()) {
            return "/articles/login";
        }
        //아이디랑 비밀번호을 찾아 select 실행
        User loginUser = userService.loginService(userDto);


        if (loginUser == null) {//계정이 존재하지 않을경우
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/articles/login";//로그인페이지로 이동
        }

        //로그인 성공

        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
        session.setAttribute("user", userDto);

        return "redirect:/";//메인화면으로 이동
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}






