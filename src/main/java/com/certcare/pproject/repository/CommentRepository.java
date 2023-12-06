package com.certcare.pproject.repository;

import com.certcare.pproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 댓글 삭제용
    Optional<Comment> findById(Long id);

    // 댓글 전체 조회용
    List<Comment> findAllByArticleId(Long articleId);
}
