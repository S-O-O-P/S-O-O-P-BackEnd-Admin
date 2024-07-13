package com.soop.pages.dashboard.controller;

import com.soop.pages.dashboard.model.dto.HoneypotDTO;
import com.soop.pages.dashboard.model.dto.InquiryDTO;
import com.soop.pages.dashboard.model.dto.NoticeDTO;
import com.soop.pages.dashboard.model.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "대시보드", description = "대시보드(관리자 메인페이지)를 관리하는 API입니다.")
@RestController
@RequestMapping("/dashboard") // 기본 경로 설정
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Operation(summary = "월별 허니팟 생성 조회", description = "생성된 허니팟 개수를 월별로 조회합니다.")
    @GetMapping("/monthly-honey-count")
    public ResponseEntity<Map<String,Object>> getMonthlyHoneyCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<Map<String, Object>> monthlyHoneyCount = dashboardService.getMonthlyHoneyCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("monthlyHoneyCount", monthlyHoneyCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "장르 카테고리별 허니팟 생성 조회", description = "장르별 생성된 허니팟 개수를 조회합니다.")
    @GetMapping("/genre-honey-count")
    public ResponseEntity<Map<String,Object>> getGenreHoneyCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<Map<String, Object>> genreHoneyCount = dashboardService.getGenreHoneyCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("genreHoneyCount", genreHoneyCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "성사된 허니팟 총개수 조회", description = "성사된 허니팟 총개수를 조회합니다.")
    @GetMapping("/total-matching-count")
    public ResponseEntity<Map<String,Object>> getTotalMatchingCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int totalMatchingCount = dashboardService.getTotalMatchingCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalMatchingCount", totalMatchingCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "일일 허니팟 성사 개수 조회", description = "일일 성사된 허니팟 개수를 조회합니다.")
    @GetMapping("/today-matching-count")
    public ResponseEntity<Map<String,Object>> getTodayMatchingCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int todayMatchingCount = dashboardService.getTodayMatchingCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayMatchingCount", todayMatchingCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "1:1 문의 전체 개수 조회", description = "1:1 문의 전체 개수를 조회합니다.")
    @GetMapping("/total-inquiry-count")
    public ResponseEntity<Map<String,Object>> getTotalInquiryCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int totalInquiryCount = dashboardService.getTotalInquiryCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalInquiryCount", totalInquiryCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "일일 1:1 문의 전체 개수 조회", description = "오늘 등록된 1:1 문의 전체 개수를 조회합니다.")
    @GetMapping("/today-inquiry-count")
    public ResponseEntity<Map<String,Object>> getTodayInquiryCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int todayInquiryCount = dashboardService.getTodayInquiryCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayInquiryCount", todayInquiryCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "신고 접수 전체 회수 조회", description = "신고 접수 전체 회수를 조회합니다.")
    @GetMapping("/total-report-count")
    public ResponseEntity<Map<String,Object>> getTotalReportCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Integer totalReportCount = dashboardService.getTotalReportCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalReportCount", totalReportCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "일일 신고 접수 회수 조회", description = "오늘 등록된 신고 접수 회수를 조회합니다.")
    @GetMapping("/today-report-count")
    public ResponseEntity<Map<String,Object>> getTodayReportCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Integer todayReportCount = dashboardService.getTodayReportCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayReportCount", todayReportCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "일일 생성된 허니팟 개수 조회", description = "오늘 생성된 허니팟 개수를 조회합니다.")
    @GetMapping("/today-honey-count")
    public ResponseEntity<Map<String,Object>> getTodayHoneyCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int todayHoneyCount = dashboardService.getTodayHoneyCount();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("todayHoneyCount", todayHoneyCount);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "신고 접수 내역 전체 조회", description = "신고 접수 내역을 전체 조회합니다.")
    @GetMapping("/reports")
    public ResponseEntity<Map<String,Object>> getReportList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<HoneypotDTO> reports = dashboardService.getReportList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("reports", reports);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "1:1 문의 접수 내역 전체 조회", description = "1:1 문의 접수 내역을 전체 조회합니다.")
    @GetMapping("/inquiries")
    public ResponseEntity<Map<String,Object>> getInquiryList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<InquiryDTO> inquiries = dashboardService.getInquiryList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("inquiries", inquiries);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "공지사항 전체 조회", description = "등록된 공지사항을 전체 조회합니다.")
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
