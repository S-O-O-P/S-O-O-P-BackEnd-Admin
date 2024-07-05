package com.soop.pages.notice.controller;

import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
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

        List<NoticeMemberFileDTO> noticeInfo = noticeService.getNoticeList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("noticeInfo", noticeInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @PostMapping("/new")
    public ResponseEntity<?> registNotice(@RequestBody NoticeMemberFileDTO noticeMemberFileDTO) {

        noticeService.registNotice(noticeMemberFileDTO);
        System.out.println("noticeFileDTO = " + noticeMemberFileDTO);

        return ResponseEntity
                .created(URI.create("new"))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> noticeDetail(@PathVariable("id") String id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        NoticeMemberFileDTO noticeMemberFileDTO = noticeService.noticeDetail(id);

        Map<String, Object> result = new HashMap<>();

        result.put("noticeFileDTO", noticeMemberFileDTO);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

}
