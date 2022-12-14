package com.example.firstProject.repository;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; //이거 없으면 쿼리문이 처음 한번만 실행되고 2번째 실행 부터는 에러가떠버림
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;



@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    @Query
    Page<Article> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);
    //Containing를 붙이면 like가 가능해진다



    @Transactional//업데이트문 사용시 필요
    @Modifying
    @Query("update Article a set a.view = a.view + 1 where a.id = :id")
    int updateView(@Param("id")Long id);

}


