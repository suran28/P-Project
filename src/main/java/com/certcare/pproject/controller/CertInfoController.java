package com.certcare.pproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CertInfoController {
    // 국가 기술 자격증 페이지
    @GetMapping("/certinfo")
    public String showCertInfoPage(Model model) {

        return "certinfo";
    }
    // 국가 기술 자격증 상세 페이지
    @GetMapping("/certdetail/{cert-id}")
    public String showCertDetailPage() {
        return "certdetail";
    }
}
