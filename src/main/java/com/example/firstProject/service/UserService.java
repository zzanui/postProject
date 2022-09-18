package com.example.firstProject.service;

import com.example.firstProject.dto.UserDto;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

}

