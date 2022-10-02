package com.example.firstProject.repository;

import com.example.firstProject.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);

    /*Spring Data Jpa에선 해당 데이터가 DB에 존재하는지 확인하기 위해 exists를 사용한다.
    해당 데이터가 존재할 경우 true, 존재하지 않을 경우 false가 리턴된다.*/
    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    /* OAuth */
    Optional<User> findByEmail(String email);

    User findByNickname(String nickname);
}


