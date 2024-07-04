package com.soop.pages.login.model.dto;

import com.soop.pages.customer.model.dto.LinkBeeUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAndLinkBeeUserDTO {

    private int userCode;
    private String id;
    private String password;
    private LinkBeeUserDTO UserRole;
}
