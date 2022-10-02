package com.example.firstProject.service;

import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.ArticleRepository;
import com.example.firstProject.repository.CommentRepository;
import com.example.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;


    /*생성*/
    @Transactional
    public Long commentSave(String nickname, Long id, CommentDto.Request dto) {
        User user = userRepository.findByNickname(nickname);
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패 : 해당 게시물이 존재하지 않습니다." + id));

        dto.setUser(user);
        dto.setArticle(article);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }

    /* UPDATE */
    @Transactional
    public void update(Long id, CommentDto.Request dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
        comment.update(dto.getComment());
    }        /* DELETE */

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
        commentRepository.delete(comment);
    }
}
