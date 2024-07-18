package com.soop.pages.honeypot.controller;

import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import com.soop.pages.honeypot.model.service.HoneypotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "허니팟 정보", description = "관리자에서 허니팟 정보를 관리하는 API입니다.")
@RestController
@RequestMapping("/honeypot")
public class HoneypotController {

    @Autowired
    private HoneypotService honeypotService;

    @Operation(summary = "허니팟 전체 조회", description = "등록되어 있는 허니팟을 전체 조회합니다.")
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getHoneypotList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<HoneypotDTO> honeypotInfo = honeypotService.getHoneypotList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("honeypotInfo", honeypotInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @Operation(summary = "허니팟 상세 조회", description = "등록되어 있는 허니팟 상세 정보를 조회합니다.")
    @Parameter(name = "honeypotCode", description = "허니팟 정보 코드 번호", in = ParameterIn.PATH)
    @GetMapping("/{honeypotCode}")
    public ResponseEntity<HoneypotDTO> getHoneypotByHoneypotCode(@PathVariable int honeypotCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        HoneypotDTO honeypot = honeypotService.getHoneypotByHoneypotCode(honeypotCode);
        if (honeypot != null) {
            return new ResponseEntity<>(honeypot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "허니팟 활성화 상태 변경", description = "등록되어 있는 허니팟의 활성화 상태(활성화/비활성화, 활성화가 default)를 변경합니다. ")
    @Parameter(name = "honeypotCode", description = "허니팟 정보 코드 번호", in = ParameterIn.PATH)
    @PostMapping("/{honeypotCode}/toggleStatus")
    public ResponseEntity<?> toggleStatus(@PathVariable int honeypotCode, @RequestBody Map<String, String> request) {
        String newStatus = request.get("newStatus");

        try {
            honeypotService.updateHoneypotStatus(honeypotCode, newStatus);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating status");
        }
    }
}

