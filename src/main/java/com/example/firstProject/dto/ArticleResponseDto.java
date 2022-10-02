package com.example.firstProject.dto;


import com.example.firstProject.entity.Article;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
 */
@Getter
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String timtstamp;
    private String username;
    private int view;

    private List<CommentDto.Response> comments;


    /* Entity -> Dto*/
    public ArticleResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.timtstamp = article.getTimestamp().toString();
        this.username = article.getUsername();
        this.view = article.getView();
        this.comments = article.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());


    }
}
