package com.certcare.pproject.domain;

import com.certcare.pproject.dto.CertInfoDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class MyCert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cert_name;
    private LocalDateTime acq_date;
    private String host;
    // 회원과 일대다 연관관계
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public CertInfoDto toCertInfoDto() {
        CertInfoDto certInfoDto = new CertInfoDto();

        certInfoDto.setCert_name(this.cert_name);
        certInfoDto.setHost(this.host);
        certInfoDto.setAcq_date(this.acq_date);

        return certInfoDto;
    }
}
