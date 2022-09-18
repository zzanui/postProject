//package com.example.firstProject.controller;
//import com.example.firstProject.dto.UserDto;
//import com.example.firstProject.entity.User;
//import com.example.firstProject.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//
//
//@RequiredArgsConstructor
//@Controller
//@Slf4j //로깅을 위한 어노테이션
//public class LoginController {
//    @Autowired//테이블을 만들어줌
//    private UserRepository loginRepository;
//
//    @PostMapping("/articles/LoginAction")//로그인//끝까지 다 안됨
//    public String SignUpAction(UserDto form, BindingResult bindingResult, HttpSession session)  {
//        User user =  form.toEntity();
//
//        if (bindingResult.hasErrors()){
//            return "/articles/login";
//        }
//        //아이디랑 비밀번호을 찾아 select 실행
//        User loginUser = loginService(user.getUsername(),user.getPassword());
//
//
//        if(loginUser == null){//계정이 존재하지 않을경우
//            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
//            return "/articles/login";//로그인페이지로 이동
//        }
//
//        //로그인 성공
//
//        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
//        session.setAttribute("user", user);
//
//
//
//        return "/articles/index";//게시판으로 이동
//        }
//
//
//        @GetMapping("/articles/logout") //로그아웃
//        public String logout(HttpSession session){
//            session.removeAttribute("user");
//            return "/articles/index";//게시판으로 이동
//        }
//
//
//
//        //아이디를 이용해 db에서 검색 후 비밀번호 또한 같을 경우 회원반납 아닐경우 null
//        public User loginService(String loginId, String password) {
//            return loginRepository.findById(loginId)
//                    .filter(m -> m.getPassword().equals(password))
//                    .orElse(null);
//
//    }
//}
