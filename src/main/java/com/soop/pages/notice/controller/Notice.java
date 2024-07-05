package com.soop.pages.notice.controller;

import com.soop.pages.notice.model.dto.NoticeFileDTO;
import com.soop.pages.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class Notice {

    @Autowired
    private NoticeService noticeService;
    @PostMapping("/noticeregist")
    public ResponseEntity<?> registNotice(@RequestBody NoticeFileDTO noticeFileDTO) {

        noticeService.registNotice(noticeFileDTO);
        System.out.println("noticeFileDTO = " + noticeFileDTO);

        return ResponseEntity
                .created(URI.create("noticeregist"))
                .build();
    }
}
