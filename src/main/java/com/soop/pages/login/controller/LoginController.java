package com.soop.pages.login.controller;

import com.soop.pages.login.model.dto.LoginAndLinkBeeUserDTO;
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

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String id = loginRequest.get("id");
        String password = loginRequest.get("password");


        LoginAndLinkBeeUserDTO loginDTO = loginService.login(id, password);

        Map<String, Object> response = new HashMap<>();
        if (loginDTO != null && "ADMIN".equals(loginDTO.getUserRole().getUserRole())) {
            response.put("id", loginDTO.getId());
            response.put("nickname", loginDTO.getUserRole().getNickname());
            response.put("userRole", loginDTO.getUserRole().getUserRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "관리자 아이디 및 비밀번호가 아닙니다. 관리자 계정으로 로그인 해주세요.");
            return ResponseEntity.ok(response);
        }
    }
}