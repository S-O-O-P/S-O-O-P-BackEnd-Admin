package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoneypotDTO {
    private int honeypotCode;
    private int interestCode;
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
}
