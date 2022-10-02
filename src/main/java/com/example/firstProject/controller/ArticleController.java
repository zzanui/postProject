package com.example.firstProject.controller;

import com.example.firstProject.annotation.LoginUser;
import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.dto.ArticleResponseDto;
import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.dto.UserSessionDto;
import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {

    @Autowired//스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결 new 노필요
    private ArticleRepository articleRepository;


    @GetMapping("/")
    public String main(){
        return "/articles/index";
    }


    @GetMapping("/articles/new")
    public String newActionForm(){
        return "articles/new";
    }

    @GetMapping("/articles/note")//게시판메인
    public String newNoteForm(Model model,@PageableDefault(size = 15,sort = "id", direction = Sort.Direction.DESC) Pageable pageable){//id기준 역순으로 정렬
        Page<Article> list = articleRepository.findAll(pageable);//조회 된 값

        model.addAttribute("posts",list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());//페이지 넘버
        model.addAttribute("next", pageable.next().getPageNumber());//페이지 넘버
        model.addAttribute("hasNext", list.hasNext());//다음 페이지 여부
        model.addAttribute("hasPrev", list.hasPrevious());//이전 페이지 여부
        return "articles/note";
    }
//    @GetMapping("/articles/read")
//    public String newReadForm(){
//        return "/articles/read";
//    }



    @PostMapping("/articles/create")//글쓰기
    public String createArticle(ArticleForm form ,Model model){
//        log.info(form.toString());

        //Dto를 내가만든Article 클래스(Entity)로 변환
        Article article = form.toEntity();
//        log.info(article.toString());
        if (article.getTitle() != null && article.getContent() != null){//제목,콘텐츠가 비어있지 않을 경우

            System.out.println("1");
            Article te = form.toEntity();
            System.out.println("\n"+(form.toString())+"\n");
            System.out.println("2");


            //리포지토리한테 엔티티를 DB에 저장하게 함
            Article saved = articleRepository.save(article);
//        log.info(saved.toString());
        }

        return "redirect:/articles/note";
    }

    @GetMapping("/articles/read/{id}")//게시판 상세조회
    public String read(@PathVariable Long id, @LoginUser UserSessionDto user, Model model){

        //게시글 변수
        Article article =articleRepository.findById(id).get();//가져와서 .get을 안붙이면 사용이 안되요 ㅠㅠ

        //댓글 변수
        ArticleResponseDto dto = new ArticleResponseDto(article);
        List<CommentDto.Response> comments = dto.getComments();



        /*댓글 관련*/
        if(comments != null && !comments.isEmpty()){
            model.addAttribute("comments",comments);
        }

        /*사용자 관련*/
        if (user != null) {
            model.addAttribute("usernickname", user.getNickname());
            /*게시글 작성자 본인인지 확인*/
            if (article.getUsername().equals(user.getUsername())){
                model.addAttribute("writer",true);
            }
        }

        model.addAttribute("posts" ,article);
        articleRepository.updateView(id);//조회수 증가
        return "/articles/read";
    }

    @GetMapping("/articles/noteDelete/{id}")//조회한 게시판 삭제
    public String delete(@PathVariable Long id, Model model){

        articleRepository.deleteById(id);

        log.info("삭제 완료");

        return "redirect:/articles/note";
    }



    @GetMapping("/articles/noteUpdate/{id}")//조회한 게시판 수정화면 이동
    public String update(@PathVariable Long id, Model model){
        //회원정보를 model에 저장
        model.addAttribute("posts" ,articleRepository.findById(id).get());//가져와서 .get을 안붙이면 사용이 안되요 ㅠㅠ
        log.info(articleRepository.findById(id).toString());
        return "/articles/noteUpdate";

    }

    @PostMapping("/articles/updateAction")//게시판 수정
    public String updateAction(ArticleForm form){

        Article article = form.toEntity();
        log.info(article.toString());
        articleRepository.save(article);

        return "redirect:/articles/note";
    }

    @Transactional
    @GetMapping("/articles/search")//게시판 검색
    public String search(String keyword, Model model, @PageableDefault(size = 15,sort = "id", direction = Sort.Direction.DESC) Pageable pageable)

    {
        Page<Article> searchList = articleRepository.findByTitleContaining(keyword,pageable);

        model.addAttribute("searchList", searchList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());//페이지 넘버
        model.addAttribute("next", pageable.next().getPageNumber());//페이지 넘버
        model.addAttribute("hasNext", searchList.hasNext());//다음 페이지 여부
        model.addAttribute("hasPrev", searchList.hasPrevious());//이전 페이지 여부



        return "/articles/search";

    }
}
