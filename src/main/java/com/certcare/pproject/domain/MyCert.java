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
    private String certName;
    private String host;
    private LocalDateTime acqDate;

    // 회원과 일대다 연관관계
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public CertInfoDto toCertInfoDto() {
        CertInfoDto certInfoDto = new CertInfoDto();

        certInfoDto.setId(this.id);
        certInfoDto.setCert_name(this.certName);
        certInfoDto.setHost(this.host);
        certInfoDto.setAcq_date(this.acqDate);

        return certInfoDto;
    }

    public MyCert(String certName, String host, LocalDateTime acqDate, Member member) {
        this.certName = certName;
        this.host = host;
        this.acqDate = acqDate;
        this.member = member;
    }

    public void update(String certName, String host, LocalDateTime acqDate) {
        if (certName != null) {
            this.certName = certName;
        }
        if (host != null) {
            this.host = host;
        }
        if (acqDate != null) {
            this.acqDate = acqDate;
        }
    }
}
