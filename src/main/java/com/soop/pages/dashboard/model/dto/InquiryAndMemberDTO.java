package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryAndMemberDTO {
    private int inquiryId;
    private String inquiryTitle;
    private String inquiryContent;
    private Date inquiryAt;
    private String inquiryStatus;
    private MemberDTO member;
}
