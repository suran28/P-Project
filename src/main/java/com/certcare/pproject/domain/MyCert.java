package com.certcare.pproject.domain;

import com.certcare.pproject.dto.MyCertInfoDto;
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

    public MyCertInfoDto toCertInfoDto() {
        MyCertInfoDto myCertInfoDto = new MyCertInfoDto();

        myCertInfoDto.setId(this.id);
        myCertInfoDto.setCert_name(this.certName);
        myCertInfoDto.setHost(this.host);

        int indexOfT = this.acqDate.toString().indexOf('T');
        if (indexOfT != -1) { // "T"를 찾은 경우
            myCertInfoDto.setAcq_date(this.acqDate.toString().substring(0, indexOfT));
        } else { // "T"를 찾지 못한 경우, 전체 문자열 반환 또는 예외 처리 등을 수행할 수 있음
            myCertInfoDto.setAcq_date(this.acqDate.toString());
        }

        return myCertInfoDto;
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
