package com.certcare.pproject.service;

import com.certcare.pproject.domain.Article;
import com.certcare.pproject.domain.Board;
import com.certcare.pproject.domain.Member;
import com.certcare.pproject.repository.ArticleRepository;
import com.certcare.pproject.repository.BoardRepository;
import com.certcare.pproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 게시물 등록
    @Transactional
    public void createArticle(String title, String body, Long memberId, int boardId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Board board = boardRepository.findByCode(boardId);

            // 게시물 생성
            Article article = new Article(title, body, member, board);
            articleRepository.save(article);
        }
    }

    // 게시물 수정
    @Transactional
    public void updateArticle() {

    }

    // 게시물 삭제

    // 댓글 등록
    @Transactional
    public void createComment(String body, Long memberId, Long ArticleId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
        }
    }
    // 댓글 삭제
}
