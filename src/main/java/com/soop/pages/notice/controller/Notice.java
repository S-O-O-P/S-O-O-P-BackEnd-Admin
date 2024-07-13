package com.soop.pages.notice.controller;

import com.soop.pages.notice.model.dto.FileDTO;
import com.soop.pages.notice.model.dto.NoticeDTO;
import com.soop.pages.notice.model.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
@Tag(name = "공지사항", description = "관리자가 공지사항을 관리할 때 사용하는 API 입니다.")
@RestController
@RequestMapping("/notice")
public class Notice {

    @Autowired
    private NoticeService noticeService;

    NoticeDTO noticeDTO = new NoticeDTO();
    FileDTO fileDTO = new FileDTO();

    @Operation(summary = "공지사항 전체 조회", description = "관리자가 등록한 공지사항을 전체 조회 합니다.")
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getNoticeList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<NoticeDTO> noticeInfo = noticeService.getNoticeList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("noticeInfo", noticeInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록 시 사용되는 api 입니다.")
    @Parameters({
            @Parameter(name = "category", description = "공지사항 카테고리 유형입니다."),
            @Parameter(name = "title", description = "공지사항 제목입니다."),
            @Parameter(name = "content", description = "공지사항 내용입니다."),
            @Parameter(name = "userCode", description = "공지사항 작성자의 번호입니다, 관리자 번호입니다."),
            @Parameter(name = "fileURL", description = "공지사항 이미지 url 입니다. null 값으로 넘어올 수 있습니다.")})
    @PostMapping("/new")
    public ResponseEntity<?> registNotice(@RequestParam("category") String category,
                                          @RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          @RequestParam("userCode") int userCode,
                                          @RequestParam(value = "fileURL", required = false) String fileURL) {

        Date now = new Date();

        noticeDTO.setCategory(category);
        noticeDTO.setTitle(title);
        noticeDTO.setContent(content);
        noticeDTO.setUserCode(userCode);
        noticeDTO.setRegDate(now);

        noticeService.registNotice(noticeDTO);

        if (fileURL != null) {

            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(fileURL);
            fileDTO.setNoticeCode(noticeDTO.getNoticeCode());
            noticeService.registNoticeFile(fileDTO);
        }

        return ResponseEntity
                .created(URI.create("new"))
                .build();
    }

    @Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 정보를 조회하는 api 입니다.")
    @Parameter(name = "id", description = "공지사항 번호", in = ParameterIn.PATH)
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

    @Operation(summary = "공지사항 수정", description = "공지사항 수정 시 사용되는 api 입니다.")
    @Parameters({
            @Parameter(name = "id", description = "수정할 공지사항의 번호입니다.", in = ParameterIn.PATH),
            @Parameter(name = "category", description = "공지사항 카테고리 유형입니다."),
            @Parameter(name = "title", description = "공지사항 제목입니다."),
            @Parameter(name = "content", description = "공지사항 내용입니다."),
            @Parameter(name = "userCode", description = "공지사항 작성자의 번호입니다, 관리자 번호입니다."),
            @Parameter(name = "fileURL", description = "공지사항 이미지 url 입니다. null 값으로 넘어올 수 있습니다.")})
    @PutMapping("/{id}")
    public ResponseEntity<?> editNotice(@PathVariable("id") int id,
                                        @RequestParam("category") String category,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String content,
                                        @RequestParam("userCode") int userCode,
                                        @RequestParam(value = "fileURL", required = false) String fileURL) {

        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeCode(id);
        noticeDTO.setCategory(category);
        noticeDTO.setTitle(title);
        noticeDTO.setContent(content);
        noticeDTO.setUserCode(userCode);

        noticeService.editNotice(noticeDTO);

        if (fileURL != null && !fileURL.isEmpty()) {
            try {
                fileDTO.setName(fileURL);
                fileDTO.setNoticeCode(id);
                noticeService.registNoticeFile(fileDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            noticeService.deleteNoticeFile(id);
        }

        return ResponseEntity
                .created(URI.create("/notice/" + id))
                .build();
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항 삭제 시 사용되는 api 입니다.")
    @Parameter(name = "id", description = "삭제할 공지사항의 번호입니다.", in = ParameterIn.PATH)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") int id, @RequestBody(required = false) Map<String, String> payload) {

        noticeService.deleteNotice(id);

        return ResponseEntity.noContent().build();
    }

}