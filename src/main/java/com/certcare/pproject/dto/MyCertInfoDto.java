package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class MyCertInfoDto {
    private Long id;
    private String cert_name;
    private LocalDateTime acq_date;
    private String host;
}
