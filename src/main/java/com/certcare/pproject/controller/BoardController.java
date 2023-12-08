package com.certcare.pproject.controller;

import com.certcare.pproject.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 카테고리 별 게시판 목록 전체 조회 test
    @GetMapping("/board/{board_id}")
    public String showBoardPage(@PathVariable String board_id, Model model) {
        String boardName = boardService.getBoardName(Integer.parseInt(board_id));
        model.addAttribute("boardName", boardName);

        // 게시판에 등록된 전체 게시물 불러오기
        return "board";
    }

    // 게시물 등록 요청
    @PostMapping("/board/{board_id}/new")
    public String articleCreateRequest(@RequestParam String title,
                                       @RequestParam String body,
                                       @PathVariable String board_id,
                                       Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memeberId = Long.valueOf(userDetails.getUsername());
        boardService.createArticle(title, body, memeberId, Integer.parseInt(board_id));

        return "";
    }

    // 게시물 수정 요청
    @PatchMapping("/board/{board_id}/{article_id}")
    public String articleUpdateRequest(@RequestParam String title,
                                       @RequestParam String body,
                                       @PathVariable String article_id) {

        boardService.updateArticle(title, body, Long.valueOf(article_id));

        return "";
    }

    // 게시물 삭제 요청
    @DeleteMapping("/{article_id}")
    public String ArticleDeleteRequest(@PathVariable String article_id) {
        boardService.deleteArticle(Long.valueOf(article_id));
        return "";
    }

    // 댓글 등록 요청
    @PostMapping("/{article_id}/comment/new")
    public String commentCreateRequest(@PathVariable String article_id,
                                       @RequestParam String body,
                                       Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memeberId = Long.valueOf(userDetails.getUsername());

        boardService.createComment(body, memeberId, Long.valueOf(article_id));
        return "";
    }

    // 댓글 삭제 요청
    @DeleteMapping("/{comment_id}")
    public String commentDeleteRequest(@PathVariable String comment_id) {
        boardService.deleteComment(Long.valueOf(comment_id));
        return "";
    }
}
