package com.certcare.pproject.domain;

import com.certcare.pproject.dto.CertDetailDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CertInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code; // 대직무분야코드
    private String majorJobName; // 대직무분야명
    private String certName; // 기사시험명
    private String w_subject; // 필기시험 과목
    private String p_subject; // 실기시험 과목
    private String w_testInfo; // 필기시험 검정방법
    private String p_testInfo; // 실기시험 검정방법
    private String w_criteria; // 필기시험 합격기준
    private String p_criteria; // 실기시험 합격기준
    @Column(length = 1000)
    private String overview; // 개요
    @Column(length = 1000)
    private String jobInfo; // 수행직무
    @Column(length = 1000)
    private String prospect; // 진로 및 전망

    public CertDetailDto toCertDetailDto(Boolean isDetail) {
        CertDetailDto dto = new CertDetailDto();

        dto.setCertName(this.certName);
        dto.setUrl("/certdetail/" + this.id);
        // 자격증 모아보기 페이지 (상세페이지 X)에 전달되는 데이터는 자격증명, url
        if (isDetail == true) {
            dto.setW_subject(this.w_subject);
            dto.setP_subject(this.p_subject);
            dto.setW_testInfo(this.w_testInfo);
            dto.setP_testInfo(this.p_testInfo);
            dto.setW_criteria(this.w_criteria);
            dto.setP_criteria(this.p_criteria);
            dto.setOverview(this.overview);
            dto.setJobInfo(this.jobInfo);
            dto.setProspect(this.prospect);
        }
        return dto;
    }
}
