package com.soop.pages.login.controller;

import com.soop.pages.login.model.dto.LinkBeeUserDTO;
import com.soop.pages.login.model.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String nickname = loginData.get("nickname");
        int userCode = Integer.parseInt(loginData.get("userCode"));

        LinkBeeUserDTO user = loginService.findByNicknameAndUserCode(nickname, userCode);

        Map<String, String> response = new HashMap<>();
        if (user != null && "ADMIN".equals(user.getUserRole())) {
            response.put("nickname", user.getNickname()); // 추가
            response.put("userRole", user.getUserRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("userRole", "USER");
            return ResponseEntity.ok(response);
        }
    }
}
