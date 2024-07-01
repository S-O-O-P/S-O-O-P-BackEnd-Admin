package com.soop.pages.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private int userCode;
    private String nickname;
    private String email;
    private String gender;
    private String profilePic;
    private String aboutMe;
    private String userRole;
    private Date signupDate;
    private String signupPlatform;
}
