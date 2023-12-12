package com.certcare.pproject.repository;

import com.certcare.pproject.domain.MajorJobCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorJobCodeRepository extends JpaRepository<MajorJobCode, Long> {
    Optional<MajorJobCode> findByCode(String code);
}
