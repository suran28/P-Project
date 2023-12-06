package com.certcare.pproject.repository;

import com.certcare.pproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    Optional<Member> findByUserId(String userId);
    boolean existsByUserId(String userId);

}
