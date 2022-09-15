package com.example.firstProject.dto;

import com.example.firstProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;




import java.time.LocalDateTime;


@AllArgsConstructor
@ToString
public class ArticleForm {


    private String title;
    private  String content;
    private String name;
    private Long id;

    private LocalDateTime timestamp;



    public Article toEntity() {
        return new Article(null, title, content,name,null,0);
    }
    public Article toUpdateEntity(){return new Article(id, title, content,name,null,0);}

}
