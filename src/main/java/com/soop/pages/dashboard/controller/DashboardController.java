package com.soop.pages.dashboard.controller;

import com.soop.pages.dashboard.model.dto.HoneypotDTO;
import com.soop.pages.dashboard.model.dto.InquiryDTO;
import com.soop.pages.dashboard.model.dto.NoticeDTO;
import com.soop.pages.dashboard.model.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard") // 기본 경로 설정
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/monthly-honey-count")
    public ResponseEntity<Map<String,Object>> getMonthlyHoneyCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<Map<String, Object>> monthlyHoneyCount = dashboardService.getMonthlyHoneyCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("monthlyHoneyCount", monthlyHoneyCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/genre-honey-count")
    public ResponseEntity<Map<String,Object>> getGenreHoneyCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<Map<String, Object>> genreHoneyCount = dashboardService.getGenreHoneyCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("genreHoneyCount", genreHoneyCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @GetMapping("/total-matching-count")
    public ResponseEntity<Map<String,Object>> getTotalMatchingCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int totalMatchingCount = dashboardService.getTotalMatchingCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalMatchingCount", totalMatchingCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/today-matching-count")
    public ResponseEntity<Map<String,Object>> getTodayMatchingCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int todayMatchingCount = dashboardService.getTodayMatchingCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayMatchingCount", todayMatchingCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @GetMapping("/total-inquiry-count")
    public ResponseEntity<Map<String,Object>> getTotalInquiryCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int totalInquiryCount = dashboardService.getTotalInquiryCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalInquiryCount", totalInquiryCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @GetMapping("/today-inquiry-count")
    public ResponseEntity<Map<String,Object>> getTodayInquiryCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int todayInquiryCount = dashboardService.getTodayInquiryCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayInquiryCount", todayInquiryCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @GetMapping("/total-report-count")
    public ResponseEntity<Map<String,Object>> getTotalReportCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Integer totalReportCount = dashboardService.getTotalReportCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalReportCount", totalReportCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/today-report-count")
    public ResponseEntity<Map<String,Object>> getTodayReportCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Integer todayReportCount = dashboardService.getTodayReportCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayReportCount", todayReportCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/today-honey-count")
    public ResponseEntity<Map<String,Object>> getTodayHoneyCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int todayHoneyCount = dashboardService.getTodayHoneyCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayHoneyCount", todayHoneyCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity<Map<String,Object>> getReportList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<HoneypotDTO> reports = dashboardService.getReportList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("reports", reports);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/inquiries")
    public ResponseEntity<Map<String,Object>> getInquiryList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<InquiryDTO> inquiries = dashboardService.getInquiryList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("inquiries", inquiries);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/notices")
    public ResponseEntity<Map<String,Object>> getLatestNotices() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<NoticeDTO> notices = dashboardService.getLatestNotices();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("notices", notices);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }
}
