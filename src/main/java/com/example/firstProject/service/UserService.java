package com.example.firstProject.service;

import com.example.firstProject.dto.UserDto;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(UserDto dto) {

        dto.setPassword(encoder.encode(dto.getPassword()));
        User user = dto.toEntity();
        userRepository.save(user);

        return user.getId();
    }


    //    아이디를 이용해 db에서 검색 후 비밀번호 또한 같을 경우 회원반납 아닐경우 null
    public User loginService(UserDto dto) {
        User user = userRepository.findById(dto.getUsername()).get();//get()함수는 인자값을 가져온다

        if (encoder.matches(user.getPassword(), dto.getPassword())) {//비밀번호 비교
            return user;
        } else {
            return null;//비밀번호가 틀릴경우 null 반환
        }
    }

    /* 회원가입 시, 유효성 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            //Service 계층에서 에러 객체의 Key 이름을 valid_%s로 설정해놨기 때문에 {{valid_필드명}}으로 작성한다.
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 아이디, 닉네임, 이메일 중복 여부 확인 */
    @Transactional(readOnly = true)
    public void checkUsernameDuplication(UserDto dto) {
        boolean usernameDuplicate = userRepository.existsByUsername(dto.toEntity().getUsername());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkNicknameDuplication(UserDto dto) {
        boolean nicknameDuplicate = userRepository.existsByNickname(dto.toEntity().getNickname());
        if (nicknameDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkEmailDuplication(UserDto dto) {
        boolean emailDuplicate = userRepository.existsByEmail(dto.toEntity().getEmail());
        if (emailDuplicate) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }





}

