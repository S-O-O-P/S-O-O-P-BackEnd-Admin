package com.soop.pages.dashboard.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private int noticeCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regDate;
    private int displayOrder;
}
