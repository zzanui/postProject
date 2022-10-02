package com.example.firstProject.dto;

import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import com.example.firstProject.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CommentDto {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class  Request{
        private Long id;
        private String comment;
        private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.mm.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.mm.dd HH:mm"));
        private User user;
        private Article article;

        public Comment toEntity(){
            Comment comments = Comment.builder()
                    .id(id)
                    .comment(comment)
                    .createdDate(createDate)
                    .modifiedDate(modifiedDate)
                    .user(user)
                    .article(article)
                    .build();

            return comments;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response{
        private Long id;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String nickname;
        private Long userId;
        private Long articleId;

        public Response(Comment comment){
            this.id = comment.getId();
            this.comment = comment.getComment();
            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();
            this.nickname = comment.getUser().getNickname();
            this.userId = comment.getUser().getId();
            this.articleId = comment.getArticle().getId();
        }
    }
}
