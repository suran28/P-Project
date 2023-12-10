package com.certcare.pproject.domain;

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
    private String w_subject; // 실기시험 과목
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

}
