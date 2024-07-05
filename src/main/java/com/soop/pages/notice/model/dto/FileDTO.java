package com.soop.pages.notice.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO {

    private int fileCode;
    private String name;
    private String noticeCode;

}
