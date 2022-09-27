package com.example.firstProject.controller;

import com.example.firstProject.dto.UserRequestDto;
import com.example.firstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class UserApiController {


    @Autowired
    private  UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PutMapping("/articles")
    public ResponseEntity<String> modify(@RequestBody UserRequestDto dto) {
        userService.modify(dto);                /* 변경된 세션 등록 */
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}

