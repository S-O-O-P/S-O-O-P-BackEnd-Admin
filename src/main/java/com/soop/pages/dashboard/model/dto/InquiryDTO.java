package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO {
    private int inquiryCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    private Date inquiryDate;
    private int adminCode;
    private String answerStatus;
    private String answer;
    private Date answerDate;
}
