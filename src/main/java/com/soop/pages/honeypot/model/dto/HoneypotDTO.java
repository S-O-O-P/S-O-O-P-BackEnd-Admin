package com.soop.pages.honeypot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoneypotDTO {
    private int honeypotCode;
    private String interestCode;
    private String honeypotTitle;
    private String honeypotContent;
    private int userCode;
    private String poster;
    private String region;
    private int totalMember;
    private Date regDate;
    private Date endDate;
    private String visibilityStatus;
    private String closureStatus;
    private int reportCount;
    private int displayOrder;


}
