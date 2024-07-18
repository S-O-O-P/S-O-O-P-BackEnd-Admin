package com.soop.pages.login.model.service;

import com.soop.pages.login.model.dao.LoginMapper;
import com.soop.pages.login.model.dto.LoginAndLinkBeeUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;
    public LoginAndLinkBeeUserDTO login(String id, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);

        return loginMapper.findByIdAndPassword(params);
    }
}
