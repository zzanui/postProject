package com.example.firstProject.controller;

import com.example.firstProject.annotation.LoginUser;
import com.example.firstProject.dto.UserDto;
import com.example.firstProject.dto.UserSessionDto;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/articles/login")//로그인
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {
        model.addAttribute("error", error);
        System.out.println(error);
        model.addAttribute("exception", exception);
        System.out.println(exception);

        return "/articles/login";
    }


    @PostMapping("/articles/LoginAction")//로그인액션
    public String SignUpAction(UserDto userDto, BindingResult bindingResult, HttpSession session) {


        if (bindingResult.hasErrors()) {
            return "/articles/login";
        }
        //아이디랑 비밀번호을 찾아 select 실행
        User loginUser = userService.loginService(userDto);
        System.out.println("LoginAction");

        if (loginUser == null) {//계정이 존재하지 않을경우//로그인 실패
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            System.out.println("아이디 또는 비밀번호가 맞지 않습니다.");
            return "/articles/login";//로그인페이지로 이동
        }

        //로그인 성공

        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
        session.setAttribute("user", userDto);
        System.out.println("로그인 성공.");
        return "redirect:/";//메인화면으로 이동
    }

    @GetMapping("/articles/logout")/* 로그아웃 */
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    /* 회원정보 수정 */
    @GetMapping("/articles/user-modify")
    public String modify(@LoginUser UserSessionDto userDto, Model model) {
        System.out.println(userDto.toString());
        if (userDto != null) {
            model.addAttribute("userID", userDto.getNickname());
            model.addAttribute("userDto", userDto);

            System.out.println(userDto.getModifiedDate());

            System.out.println(userDto.toString());
        }
        return "/articles/user-modify";
    }
}







