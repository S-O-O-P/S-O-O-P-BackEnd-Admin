package com.soop.pages.login.model.service;

import com.soop.pages.login.model.dao.LoginMapper;
import com.soop.pages.login.model.dto.LinkBeeUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;
    public LinkBeeUserDTO findByNicknameAndUserCode(String nickname, int userCode) {
        return loginMapper.findByNicknameAndUserCode(nickname, userCode);
    }
}
