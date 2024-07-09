package com.soop.pages.notice.controller;

import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
import com.soop.pages.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/notice")
public class Notice {

    @Autowired
    private NoticeService noticeService;

    NoticeMemberFileDTO noticeMemberFileDTO = new NoticeMemberFileDTO();
    FileDTO fileDTO = new FileDTO();

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
    public ResponseEntity<?> registNotice(@RequestParam("category") String category,
                                          @RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          @RequestParam("userCode") int userCode,
                                          @RequestParam("file") MultipartFile file) {

        String root = System.getProperty("user.dir");
        String filePath = root + "/src/main/resources/static/uploadImages";
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originFileName = file.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        String savedName = UUID.randomUUID() + ext;

        try {
            file.transferTo(new File(filePath + "/" + savedName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Date now = new Date();

        noticeMemberFileDTO.setCategory(category);
        noticeMemberFileDTO.setTitle(title);
        noticeMemberFileDTO.setContent(content);
        noticeMemberFileDTO.setUserCode(userCode);
        noticeMemberFileDTO.setRegDate(now);

        noticeService.registNotice(noticeMemberFileDTO);

        fileDTO.setName(originFileName);

        fileDTO.setNoticeCode(noticeMemberFileDTO.getNoticeCode());

        noticeService.registNoticeFile(fileDTO);

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

    @PutMapping("/{id}")
    public ResponseEntity<?> editNotice(@PathVariable("id") int id, @RequestBody NoticeMemberFileDTO noticeMemberFileDTO) {

        noticeMemberFileDTO.setNoticeCode(id);
        noticeService.editNotice(noticeMemberFileDTO);
        System.out.println("noticeFileDTO = " + noticeMemberFileDTO);

        return ResponseEntity
                .created(URI.create("/notice/" + id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        noticeService.deleteNotice(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
