package com.certcare.pproject.repository;

import com.certcare.pproject.domain.CertInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertInfoRepository extends JpaRepository<CertInfo, Long> {
    List<CertInfo> findAllByCode(String code);
}
