package com.soop.pages.notice.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoticeMemberFileDTO {

    private int noticeCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    private Date regDate;

    private FileDTO fileDTO;
    private MemberDTO memberDTO;

}