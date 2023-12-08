package com.certcare.pproject.service;

import com.certcare.pproject.domain.Article;
import com.certcare.pproject.domain.Board;
import com.certcare.pproject.domain.Comment;
import com.certcare.pproject.domain.Member;
import com.certcare.pproject.repository.ArticleRepository;
import com.certcare.pproject.repository.BoardRepository;
import com.certcare.pproject.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    // 게시판 명 가져오기
    @Transactional
    public String getBoardName(int code) {
        Board board = boardRepository.findByCode(code);
        return board.getBoardName();
    }

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
    public void updateArticle(String title, String body, Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article updatedArticle = optionalArticle.get();
            updatedArticle.update(title, body);
            articleRepository.save(updatedArticle);
        }
    }

    // 게시물 삭제
    @Transactional
    public void deleteArticle(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            articleRepository.delete(article);
        }
    }

    // 댓글 등록
    @Transactional
    public void createComment(String body, Long memberId, Long ArticleId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<Article> optionalArticle = articleRepository.findById(ArticleId);
        if (optionalMember.isPresent() && optionalArticle.isPresent()) {
            Member member = optionalMember.get();
            Article article = optionalArticle.get();

            Comment comment = new Comment(member, article, body);
            commentRepository.save(comment);
        }
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.delete(optionalComment.get());
        }
    }
}
