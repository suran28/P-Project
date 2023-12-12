package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CertDetailDto {
    private String certName;
    private String url;
    private String overview; // 개요
    private String jobInfo; // 수행직무
    private String prospect; // 진로 및 전망
    private String w_subject; // 필기시험 과목
    private String p_subject; // 실기시험 과목
    private String w_testInfo; // 필기시험 검정방법
    private String p_testInfo; // 실기시험 검정방법
    private String w_criteria; // 필기시험 합격기준
    private String p_criteria; // 실기시험 합격기준
}
