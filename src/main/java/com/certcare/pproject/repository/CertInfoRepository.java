package com.certcare.pproject.repository;

import com.certcare.pproject.domain.CertInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertInfoRepository extends JpaRepository<CertInfo, Long> {
}
