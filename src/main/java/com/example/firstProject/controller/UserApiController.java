package com.example.firstProject.controller;

import com.example.firstProject.dto.UserRequestDto;
import com.example.firstProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST API Controller
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {


    @Autowired
    private final UserService userService;
    @Autowired
    private final AuthenticationManager authenticationManager;


    @PutMapping("/user")
    public ResponseEntity<String> modify(@RequestBody UserRequestDto dto) {

        System.out.println(" 수정 테스트 0");
        userService.modify(dto);
        System.out.println(" 수정 테스트 1");


        /* 변경된 세션 등록 */
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(" 수정 테스트 2");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }






}

