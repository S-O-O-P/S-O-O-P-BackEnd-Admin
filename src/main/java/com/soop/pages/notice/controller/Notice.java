package com.soop.pages.notice.controller;

import com.soop.pages.customer.model.dto.LinkBeeUserDTO;
import com.soop.pages.notice.model.dto.NoticeFileDTO;
import com.soop.pages.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class Notice {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> getNoticeList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<NoticeFileDTO> noticeInfo = noticeService.getNoticeList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("noticeInfo", noticeInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @PostMapping("/noticeregist")
    public ResponseEntity<?> registNotice(@RequestBody NoticeFileDTO noticeFileDTO) {

        noticeService.registNotice(noticeFileDTO);
        System.out.println("noticeFileDTO = " + noticeFileDTO);

        return ResponseEntity
                .created(URI.create("noticeregist"))
                .build();
    }
}
