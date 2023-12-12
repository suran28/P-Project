package com.certcare.pproject.repository;

import com.certcare.pproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByCode(String code);
}
