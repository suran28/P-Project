package com.certcare.pproject.service;

import com.certcare.pproject.domain.CertInfo;
import com.certcare.pproject.domain.MajorJobCode;
import com.certcare.pproject.dto.CertDetailDto;
import com.certcare.pproject.dto.CertInfoDto;
import com.certcare.pproject.repository.CertInfoRepository;
import com.certcare.pproject.repository.MajorJobCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CertService {
    private final CertInfoRepository certInfoRepository;
    private final MajorJobCodeRepository majorJobCodeRepository;

    // 국가 기술 자격증 메뉴, 대직무분야별 자격증 모아보기 (상세페이지 X)
    @Transactional
    public List<CertInfoDto> getCertListsByJobCode() {
        Boolean isDetail = false; // (상세페이지 X)

        // 반환할 대직무분야명별 자격증 리스트 데이터 (대직무분야명 + 그에 해당하는 자격증 리스트)
        List<CertInfoDto> certInfoDtos = new ArrayList<>();

        // 대직무코드를 전부 가져온다
        List<MajorJobCode> majorJobCodes = majorJobCodeRepository.findAll();
        List<String> codes = majorJobCodes.stream()
                .map(majorJobCode -> majorJobCode.getCode())
                .collect(Collectors.toList());

        // 각 코드에 해당하는 자격증 리스트 dto 생성
        for (String code : codes) {
            List<CertInfo> certInfos = certInfoRepository.findAllByCode(code);

            if (certInfos.isEmpty()) {
                continue;
            }
            List<CertDetailDto> certDetailDtos = certInfos.stream()
                    .map(certInfo -> certInfo.toCertDetailDto(isDetail))
                    .collect(Collectors.toList());

            CertInfoDto certInfoDto = new CertInfoDto();
            Optional<MajorJobCode> optionalMajorJobCode = majorJobCodeRepository.findByCode(code);
            if (optionalMajorJobCode.isPresent()) {
                certInfoDto.setMajorJobName(optionalMajorJobCode.get().getJobName());
            }
            certInfoDto.setCertDetailDtos(certDetailDtos);
            certInfoDtos.add(certInfoDto);
        }
        return certInfoDtos;
    }

    // 자격증 상세페이지
    @Transactional
    public CertDetailDto getCertDetail(Long certId){
        Boolean isDetail = true;

        Optional<CertInfo> optionalCertInfo = certInfoRepository.findById(certId);
        if (optionalCertInfo.isPresent()) {
            CertInfo certInfo = optionalCertInfo.get();
            return certInfo.toCertDetailDto(isDetail);
        } else{
            throw new RuntimeException("자격증 정보가 없습니다");
        }
    }
}
