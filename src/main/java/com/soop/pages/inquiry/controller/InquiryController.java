package com.soop.pages.inquiry.controller;

import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import com.soop.pages.inquiry.model.dto.InquiryDTO;
import com.soop.pages.inquiry.model.service.InquiryService;
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
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "1:1 문의", description = "관리자가 1:1 문의를 관리할 때 사용하는 API 입니다.")
@RestController
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Operation(summary = "1:1 문의 전체 조회", description = "고객센터에 등록되어 있는 1:1 문의 전체 리스트를 조회합니다.")
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getInquiryList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<InquiryDTO> inquiryInfo = inquiryService.getInquiryList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("inquiryInfo", inquiryInfo);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @Operation(summary = "1:1 문의 상세 조회", description = "1:1 문의 상세 정보를 조회합니다.")
    @Parameter(name = "inquiryCode", description = "1:1 문의 정보 코드인 inquiryCode를 통해 해당 1:1 문의 상세 정보를 조회합니다.", in = ParameterIn.PATH)
    @GetMapping("/{inquiryCode}")
    public ResponseEntity<InquiryDTO> getInquiryByInquiryCode(@PathVariable int inquiryCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        InquiryDTO inquiry = inquiryService.getInquiryByInquiryCode(inquiryCode);
        if (inquiry != null) {
            return new ResponseEntity<>(inquiry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "1:1 문의 답변 등록", description = "1:1 문의에 대한 관리자 답변을 등록합니다.")
    @Parameter(name = "inquiryCode", description = "1:1 문의 정보 코드인 inquiryCode를 통해 해당 1:1 문의에 대한 관리자 답변을 조회합니다.", in = ParameterIn.PATH)
    @PostMapping("/{inquiryCode}/answer")
    public ResponseEntity<Void> updateInquiryAnswer(@PathVariable int inquiryCode, @RequestBody Map<String, String> request) {
        String answer = request.get("answer");
        Date answerDate = new Date(System.currentTimeMillis()); // Current date and time


        boolean isUpdated = inquiryService.updateInquiryAnswer(inquiryCode, answer, answerDate);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "1:1 문의 답변 상태 변경", description = "1:1 문의에 대한 관리자 답변 여부에 따라 답변 상태를 변경합니다.")
    @Parameter(name = "inquiryCode", description = "1:1 문의 정보 코드인 inquiryCode를 통해 해당 1:1 문의에 대한 관리자 답변 상태를 변경합니다.", in = ParameterIn.PATH)
    @PatchMapping("/{inquiryCode}/status")
    public ResponseEntity<Void> updateInquiryStatus(@PathVariable int inquiryCode, @RequestBody Map<String, String> request) {
        String answerStatus = request.get("answerStatus");

        boolean isUpdated = inquiryService.updateInquiryStatus(inquiryCode, answerStatus);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



