package com.soop.pages.customer.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkBeeUserDTO {
    private int userCode;
    private int honeypotCode;
    private String nickname;
    private String email;
    private String visibilityStatus;
    private String gender;
    private String profilePic;
    private String aboutMe;
    private String userRole;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signupDate;
    private String signupPlatform;
}
