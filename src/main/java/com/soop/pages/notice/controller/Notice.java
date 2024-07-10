package com.soop.pages.notice.controller;

import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeDTO;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/notice")
public class Notice {

    @Autowired
    private NoticeService noticeService;

    NoticeDTO noticeDTO = new NoticeDTO();
    FileDTO fileDTO = new FileDTO();

    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> getNoticeList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<NoticeDTO> noticeInfo = noticeService.getNoticeList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("noticeInfo", noticeInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> registNotice(@RequestParam("category") String category,
                                          @RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          @RequestParam("userCode") int userCode,
                                          @RequestParam(value = "file", required = false) MultipartFile file) {

        Date now = new Date();

        noticeDTO.setCategory(category);
        noticeDTO.setTitle(title);
        noticeDTO.setContent(content);
        noticeDTO.setUserCode(userCode);
        noticeDTO.setRegDate(now);

        noticeService.registNotice(noticeDTO);

        if (file != null && !file.isEmpty()) {
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

            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(savedName);
            fileDTO.setNoticeCode(noticeDTO.getNoticeCode());
            noticeService.registNoticeFile(fileDTO);
        }

        return ResponseEntity
                .created(URI.create("new"))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> noticeDetail(@PathVariable("id") String id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int code = Integer.parseInt(id);

        NoticeDTO noticeDTO1 = noticeService.noticeDetail(code);

        FileDTO fileDTO1 = noticeService.noticeDetailFile(code);

        Map<String, Object> result = new HashMap<>();

        result.put("noticeDTO", noticeDTO1);
        result.put("fileDTO", fileDTO1);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] image(@RequestParam String name) throws IOException {
        String root = System.getProperty("user.dir");
        String filePath = root + "/src/main/resources/static/uploadImages/";

        Path imagePath = Paths.get(filePath, name);

        System.out.println("imagePath = " + imagePath);
        return Files.readAllBytes(imagePath);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editNotice(@PathVariable("id") int id,
                                        @RequestParam("category") String category,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String content,
                                        @RequestParam("userCode") int userCode,
                                        @RequestParam(value = "file", required = false) MultipartFile file) {

        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeCode(id);
        noticeDTO.setCategory(category);
        noticeDTO.setTitle(title);
        noticeDTO.setContent(content);
        noticeDTO.setUserCode(userCode);
        noticeService.editNotice(noticeDTO);

        FileDTO fileDTO1 = new FileDTO();
        System.out.println("fileDTO1 getName 출력입니다. = " + fileDTO1.getName());

        if (file != null) {

            FileDTO existingFile = noticeService.noticeDetailFile(id);
            if (existingFile != null) {
                String filePath = "src/main/resources/static/uploadImages/" + existingFile.getName();
                File fileToDelete = new File(filePath);
                if (fileToDelete.exists() && fileToDelete.delete()) {
                    System.out.println("기존 파일 삭제 성공");
                } else {
                    System.out.println("기존 파일 삭제 실패");
                }
            }

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

            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(savedName);
            fileDTO.setNoticeCode(noticeDTO.getNoticeCode());
            noticeService.editNoticeFile(fileDTO);
        } else {
            noticeService.deleteNoticeFile(id);
        }

        return ResponseEntity
                .created(URI.create("/notice/" + id))
                .build();
    }


    @DeleteMapping("/{id}")
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
