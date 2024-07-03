package com.soop.pages.inquiry.controller;

import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import com.soop.pages.inquiry.model.dto.InquiryDTO;
import com.soop.pages.inquiry.model.service.InquiryService;
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

@RestController
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getInquiryList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<InquiryDTO> inquiryInfo = inquiryService.getInquiryList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("inquiryInfo", inquiryInfo);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

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



