package com.certcare.pproject.service;

import com.certcare.pproject.domain.CertInfo;
import com.certcare.pproject.domain.MajorJobCode;
import com.certcare.pproject.dto.CertDetailDto;
import com.certcare.pproject.dto.CertInfoDto;
import com.certcare.pproject.repository.CertInfoRepository;
import com.certcare.pproject.repository.MajorJobCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CertService {
    private final CertInfoRepository certInfoRepository;
    private final MajorJobCodeRepository majorJobCodeRepository;

    // 국가 기술 자격증 메뉴, 대직무분야별 자격증 모아보기 (상세페이지 X)
//    public List<CertDetailDto> getCertListsByJobCode() {
//        Boolean isDetail = false; // (상세페이지 X)
//        List<CertInfoDto> certInfoDtos = new ArrayList<>();
//
//        List<MajorJobCode> majorJobCodes = majorJobCodeRepository.findAll();
//        List<String> codes = majorJobCodes.stream()
//                .map(majorJobCode -> majorJobCode.getCode())
//                .collect(Collectors.toList());
//
//        for (String code : codes) {
//            List<CertInfo> certInfos = certInfoRepository.findAllByCode(code);
//            List<CertDetailDto> certDetailDtos = certInfos.stream()
//                    .map(certInfo -> certInfo.toCertDetailDto(isDetail))
//                    .collect(Collectors.toList());
//
//            CertInfoDto certInfoDto = new CertInfoDto();
//            certInfoDto.setMajorJobName(c);
//        }
//    }
}
