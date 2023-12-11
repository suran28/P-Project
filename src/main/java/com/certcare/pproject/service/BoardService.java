package com.certcare.pproject.service;

import com.certcare.pproject.domain.Article;
import com.certcare.pproject.domain.Board;
import com.certcare.pproject.domain.Comment;
import com.certcare.pproject.domain.Member;
import com.certcare.pproject.dto.ArticleDto;
import com.certcare.pproject.dto.CommentDto;
import com.certcare.pproject.repository.ArticleRepository;
import com.certcare.pproject.repository.BoardRepository;
import com.certcare.pproject.repository.CommentRepository;
import com.certcare.pproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 게시판에 등록된 모든 글 가져오기
    @Transactional
    public List<ArticleDto> getArticleListsByBoardCode(int code) {
        List<Article> articles = articleRepository.findAllByBoardCode(code);
        Boolean detail = false;
        Boolean isWriter = false;
        List<ArticleDto> dtos = articles.stream()
                .map(article -> article.toArticleDto(detail, isWriter))
                .collect(Collectors.toList());

        return dtos;
    }

    // 게시글 페이지 상세 조회
    @Transactional
    public ArticleDto getArticle(Long articleId, Long memberId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            Boolean detail = true;  // 상세페이지는 true, dto에 게시물 본문 내용 담아서 보내주기

            Boolean isWriter = false;
            if (memberId.equals(article.getMember().getId())) {
                isWriter = true;
            }
            return article.toArticleDto(detail, isWriter);
        } else {
            throw new RuntimeException("게시물이 존재하지 않습니다");
        }
    }

    // 게시글 별 댓글 조회
    @Transactional
    public List<CommentDto> getComments(Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        List<CommentDto> dtos = comments.stream()
                .map(comment -> comment.toCommentDto())
                .collect(Collectors.toList());

        return dtos;
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
