package com.certcare.pproject.controller;

import com.certcare.pproject.dto.CertDetailDto;
import com.certcare.pproject.dto.CertInfoDto;
import com.certcare.pproject.service.CertService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class CertInfoController {
    private final CertService certService;
    // 국가 기술 자격증 페이지
    @GetMapping("/certinfo")
    public String showCertInfoPage(Model model) {
        List<CertInfoDto> dtos = certService.getCertListsByJobCode();
        model.addAttribute("certInfo", dtos);
        return "certInfo";
    }

    // 국가 기술 자격증 상세 페이지
    @GetMapping("/certdetail/{cert_id}")
    public String showCertDetailPage(@PathVariable String cert_id, Model model) {
        CertDetailDto dto = certService.getCertDetail(Long.valueOf(cert_id));
        model.addAttribute("certDetail", dto);
        return "certdetail";
    }
}
