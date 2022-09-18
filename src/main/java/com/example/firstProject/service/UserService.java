package com.example.firstProject.service;

import com.example.firstProject.dto.UserDto;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
        User user =  userRepository.findById(dto.getUsername()).get();//get()함수는 인자값을 가져온다

        if(encoder.matches(user.getPassword(),dto.getPassword())){//비밀번호 비교
            return user;
        }
        else {
            return null;//비밀번호가 틀릴경우 null 반환
        }
//        return userRepository.findById(dto.getUsername())
//                .filter(m -> m.getPassword().equals(dto.getPassword()))//조건
//                .orElse(null);
    }
}

