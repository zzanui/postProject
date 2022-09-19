package com.example.firstProject.validate;

import com.example.firstProject.dto.UserDto;
import com.example.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<UserDto> {
    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserDto dto, Errors errors) {
        System.out.println("doValidate");
        if (userRepository.existsByUsername(dto.toEntity().getUsername())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
