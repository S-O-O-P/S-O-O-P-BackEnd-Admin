package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private int noticeCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    private Date regDate;
    private int displayOrder;
}
