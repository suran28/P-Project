package com.certcare.pproject.repository;

import com.certcare.pproject.domain.Member;
import com.certcare.pproject.domain.MyCert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyCertRepository extends JpaRepository<MyCert, Long>{
    List<MyCert> findAllByMemberId(Long id);
}
