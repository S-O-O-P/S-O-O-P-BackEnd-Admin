package com.soop.pages.notice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
