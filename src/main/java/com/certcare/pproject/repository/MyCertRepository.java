package com.certcare.pproject.repository;

import com.certcare.pproject.domain.MyCert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyCertRepository extends JpaRepository<MyCert, Long>{
    List<MyCert> findAllByMemberId(Long id);

    Optional<MyCert> findById(Long id);
}
