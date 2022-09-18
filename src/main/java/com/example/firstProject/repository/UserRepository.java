package com.example.firstProject.repository;

import com.example.firstProject.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(@Param("username") String username);
}


