package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeAndMemberDTO {
    private int noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeAt;
    private String noticeExposureStatus;
    private MemberDTO member;
}
