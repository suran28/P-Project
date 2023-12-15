package com.certcare.pproject.controller;

import com.certcare.pproject.dto.ArticleDto;
import com.certcare.pproject.dto.ArticleRequestDto;
import com.certcare.pproject.dto.CommentDto;
import com.certcare.pproject.dto.CommentRequestDto;
import com.certcare.pproject.jwt.TokenProvider;
import com.certcare.pproject.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final TokenProvider tokenProvider;

    // 카테고리 별 게시판 목록 전체 조회
    @GetMapping("/board/{board_code}/{page}")
    public String showBoardPage(@PathVariable String board_code, Model model) {
        String boardName = boardService.getBoardName(board_code);
        model.addAttribute("boardName", boardName);

        // 게시판에 등록된 전체 게시물 불러오기
        List<ArticleDto> dtos = boardService.getArticleListsByBoardCode(board_code);
        model.addAttribute("articleList", dtos);

        return "board";
    }

    // 게시물 상세 페이지
    @GetMapping("/board/{board_code}/article/{article_id}")
    public String showArticlePage(@PathVariable String article_id,
                                  @PathVariable String board_code,
                                  Model model,
                                  @CookieValue(name = "accessToken", defaultValue = "default") String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memberId = Long.valueOf(userDetails.getUsername());

        ArticleDto articleDto = boardService.getArticle(Long.valueOf(article_id), memberId);
        List<CommentDto> commentDtos = boardService.getComments(Long.valueOf(article_id), memberId);

        // 본인 확인 여부를 보내줘야 함,
        String boardName = boardService.getBoardName(board_code);
        model.addAttribute("boardName", boardName);
        model.addAttribute("article", articleDto);
        model.addAttribute("commentList", commentDtos);

        return "article";
    }

    // 게시물 등록 페이지로 이동
    @GetMapping("/board/{board_code}/article/new")
    public String showArticleCreatePage(Model model,
                                        @PathVariable String board_code) {
        String boardName = boardService.getBoardName(board_code);
        model.addAttribute("boardName", boardName);
        return "article";
    }

    // 게시물 등록 요청
    @PostMapping("/board/{board_code}/article/new")
    @ResponseBody
    public ResponseEntity<String> articleCreateRequest(@RequestBody ArticleRequestDto dto,
                                       @PathVariable String board_code,
                                       @CookieValue(name = "accessToken", defaultValue = "default") String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memeberId = Long.valueOf(userDetails.getUsername());
        Long id = boardService.createArticle(dto.getTitle(), dto.getBody(), memeberId, board_code);

        return ResponseEntity.ok("/board/"+ board_code + "/article/" + id);
    }


    // 게시물 수정 요청
    @PatchMapping("/board/{board_code}/article/{article_id}")
    @ResponseBody
    public ResponseEntity<String> articleUpdateRequest(@RequestBody ArticleRequestDto dto,
                                       @PathVariable String board_code,
                                       @PathVariable String article_id) {

        Long id = boardService.updateArticle(dto.getTitle(), dto.getBody(), Long.valueOf(article_id));

        return ResponseEntity.ok("/board/"+ board_code + "/article/" + id);
    }

    // 게시물 삭제 요청
    @DeleteMapping("/board/{board_code}/article/{article_id}")
    @ResponseBody
    public ResponseEntity<String> ArticleDeleteRequest(@PathVariable String article_id,
                                       @PathVariable String board_code) {
        boardService.deleteArticle(Long.valueOf(article_id));
        return ResponseEntity.ok("/board/"+ board_code + "/1");
    }

    // 댓글 등록 요청
    @PostMapping("/{article_id}/comment/new")
    @ResponseBody
    public ResponseEntity<String> commentCreateRequest(@PathVariable String article_id,
                                       @RequestBody CommentRequestDto dto,
                                       @CookieValue(name = "accessToken", defaultValue = "default") String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memeberId = Long.valueOf(userDetails.getUsername());

        boardService.createComment(dto.getBody(), memeberId, Long.valueOf(article_id));

        return ResponseEntity.ok("댓글 등록 성공");
    }

    // 댓글 삭제 요청
    @DeleteMapping("/{comment_id}")
    @ResponseBody
    public ResponseEntity<String> commentDeleteRequest(@PathVariable String comment_id) {
        boardService.deleteComment(Long.valueOf(comment_id));
        return ResponseEntity.ok("댓글 삭제 성공");
    }
}
