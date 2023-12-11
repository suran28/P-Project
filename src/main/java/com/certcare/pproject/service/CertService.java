package com.certcare.pproject.service;

import com.certcare.pproject.domain.CertInfo;
import com.certcare.pproject.repository.CertInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CertService {
    private final CertInfoRepository certInfoRepository;

//    public List<CertInfo> getCertLists() {
//
//    }
}
