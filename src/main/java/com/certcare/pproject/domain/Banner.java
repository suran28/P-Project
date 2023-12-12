package com.certcare.pproject.domain;

import com.certcare.pproject.dto.BannerDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String host;
    private String certName;
    private String majorJobName;
    private String majorJobCode;
    private String overview;
    private String certInfoId;

    public BannerDto toBannerDto() {
        BannerDto dto = new BannerDto();
        dto.setHost(this.host);
        dto.setCertName(this.certName);
        dto.setMajorJobName(this.majorJobName);
        dto.setOverview(this.overview);
        dto.setMajorJobCode(this.majorJobCode);
        dto.setUrl("/certdetail/"+this.certInfoId);
        return dto;
    }
}
