package com.certcare.pproject.controller;

import com.certcare.pproject.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

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
                                       @PathVariable String board_id,
                                       Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memeberId = Long.valueOf(userDetails.getUsername());
        boardService.createArticle(title, body, memeberId, Integer.parseInt(board_id));

        return "";
    }

    // 게시물 삭제 요청

    // 댓글 등록 요청
    @PostMapping("/{article_id}/comment/new")
    public String commentCreateRequest(@PathVariable String article_id,
                                       @RequestParam String body,
                                       Authentication authentication) {

        return "";
    }
}
