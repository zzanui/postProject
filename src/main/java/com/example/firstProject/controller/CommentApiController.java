package com.example.firstProject.controller;


import com.example.firstProject.annotation.LoginUser;
import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.dto.UserSessionDto;
import com.example.firstProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
class CommentApiController {

    private final CommentService commentService;

    /*댓글 생성*/
    @PostMapping("/articles/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentDto.Request dto,
                                      @LoginUser UserSessionDto userSessionDto) {
        return ResponseEntity.ok(commentService.commentSave(userSessionDto.getNickname(), id, dto));
    }

    /* UPDATE */
    @PutMapping({"/articles/{id}/comments/{commentId}"})
    public ResponseEntity update(@PathVariable Long commentId, @RequestBody CommentDto.Request dto) {
        commentService.update(commentId, dto);
        return ResponseEntity.ok(commentId);
    }

    /* DELETE */
    @DeleteMapping("/articles/{id}/comments/{commentId}")
    public ResponseEntity delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok(commentId);
    }

}
