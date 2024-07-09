package com.soop.pages.notice.controller;

import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeMemberFileDTO;
import com.soop.pages.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/notice")
public class Notice {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getNoticeList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<NoticeMemberFileDTO> noticeInfo = noticeService.getNoticeList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("noticeInfo", noticeInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<?> registNotice(@RequestParam("category") String category,
                                          @RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          @RequestParam("userCode") int userCode,
                                          @RequestParam(value = "file", required = false) MultipartFile file) {

        NoticeMemberFileDTO noticeMemberFileDTO = new NoticeMemberFileDTO();
        noticeMemberFileDTO.setCategory(category);
        noticeMemberFileDTO.setTitle(title);
        noticeMemberFileDTO.setContent(content);
        noticeMemberFileDTO.setUserCode(userCode);
        noticeMemberFileDTO.setRegDate(new Date(System.currentTimeMillis()));

        noticeService.registNotice(noticeMemberFileDTO);

        if (file != null && !file.isEmpty()) {
            String root = System.getProperty("user.dir");
            String filePath = root + "/src/main/resources/static/uploadImages";
            File dir = new File(filePath);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originFileName = file.getOriginalFilename();
            String ext = originFileName != null ? originFileName.substring(originFileName.lastIndexOf(".")) : "";
            String savedName = UUID.randomUUID() + ext;

            try {
                file.transferTo(new File(filePath + "/" + savedName));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
            }

            // Create and set FileDTO
            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(originFileName);
            fileDTO.setNoticeCode(noticeMemberFileDTO.getNoticeCode());

            noticeMemberFileDTO.setFileDTO(fileDTO);

            // Save file information
            noticeService.registNoticeFile(fileDTO);
        }

        return ResponseEntity.created(URI.create("/notice/" + noticeMemberFileDTO.getNoticeCode())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> noticeDetail(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        NoticeMemberFileDTO noticeMemberFileDTO = noticeService.noticeDetail(id);
        if (noticeMemberFileDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("noticeFileDTO", noticeMemberFileDTO);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editNotice(@PathVariable("id") int id,
                                        @RequestParam("category") String category,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String content,
                                        @RequestParam("userCode") int userCode,
                                        @RequestParam(value = "file", required = false) MultipartFile file) {

        NoticeMemberFileDTO noticeMemberFileDTO = new NoticeMemberFileDTO();
        noticeMemberFileDTO.setNoticeCode(id);
        noticeMemberFileDTO.setCategory(category);
        noticeMemberFileDTO.setTitle(title);
        noticeMemberFileDTO.setContent(content);
        noticeMemberFileDTO.setUserCode(userCode);
        noticeMemberFileDTO.setRegDate(new Date(System.currentTimeMillis())); // Set the current date

        // Handle file upload if a file is provided
        if (file != null && !file.isEmpty()) {
            String root = System.getProperty("user.dir");
            String filePath = root + "/src/main/resources/static/uploadImages";
            File dir = new File(filePath);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originFileName = file.getOriginalFilename();
            String ext = originFileName != null ? originFileName.substring(originFileName.lastIndexOf(".")) : "";
            String savedName = UUID.randomUUID() + ext;

            try {
                file.transferTo(new File(filePath + "/" + savedName));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
            }

            // Create and set FileDTO
            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(originFileName);
            fileDTO.setNoticeCode(id); // Use the existing notice code

            noticeMemberFileDTO.setFileDTO(fileDTO);

            // Update the file information in the service
            noticeService.registNoticeFile(fileDTO);
        }

        noticeService.editNotice(noticeMemberFileDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteNotice(@PathVariable("id") int id, @RequestBody(required = false) Map<String, String> payload) {
        if (payload != null && payload.containsKey("fileName")) {
            String fileName = payload.get("fileName");
            String filePath = "src/main/resources/static/uploadImages/" + fileName;

            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    System.out.println("파일 삭제 성공");
                } else {
                    System.out.println("파일 삭제 실패");
                }
            } else {
                System.out.println("파일 없음");
            }
        }

        noticeService.deleteNotice(id);

        return ResponseEntity.noContent().build();
    }
}
