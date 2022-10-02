package com.example.firstProject.dto;

import com.example.firstProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;




import java.time.LocalDateTime;


@AllArgsConstructor
@ToString
@Builder
public class ArticleForm {

    private String  username;
    private String title;
    private  String content;
    private String nickname;
    private Long id;

    private LocalDateTime timestamp;



    public Article toEntity() {

        Article article = Article.builder()
                .id(id)
                .title(title)
                .content(content)
                .username(username)
                .nickname(nickname)
                .timestamp(null)
                .view(0)
                .build();

        return article;
    }



}
