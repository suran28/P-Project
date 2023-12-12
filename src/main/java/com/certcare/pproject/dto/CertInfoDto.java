package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CertInfoDto {
    // 국가 기술 자격증 페이지 용
    // 대직무분야별 기사 자격증 목록 출력

    private String majorJobName;
    private List<CertDetailDto> certDetailDtos;
}
