package com.soop.pages.cultureInfo.controller;

import com.soop.pages.cultureInfo.model.dto.CultureInfoDTO;
import com.soop.pages.cultureInfo.model.service.CultureInfoService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cultureinfo")
public class CultureInfoController {

  private final CultureInfoService cultureInfoService;

  public CultureInfoController(CultureInfoService cultureInfoService) {this.cultureInfoService = cultureInfoService;}

  // CultureInfo 전체 리스트 조회
  @GetMapping("/early")
  public ResponseEntity<Map<String, Object>> getEarlyList() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    List<CultureInfoDTO> earlyBirdList = cultureInfoService.selectAllCultureInfo(); // 전체 리스트 조회
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("earlyBirdList", earlyBirdList); // 응답 데이터 저장

    return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
  }

  // 얼리버드 공연/전시 정보 상세 조회
  @GetMapping("/early/{earlyCode}")
  public ResponseEntity<Map<String, Object>> findEarlyInfoByCode(@PathVariable int earlyCode) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    CultureInfoDTO foundEarlyInfo = cultureInfoService.selectEarlyInfoByEarlyBirdCode(earlyCode);

    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("foundEarlyInfo", foundEarlyInfo);

    return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
  }

  // 얼리버드 공연/전시 정보 등록
  @PostMapping("/early")
  public void registerEarlyInfo(@RequestBody CultureInfoDTO newInfo) {
    cultureInfoService.registerNewEarlyInfo(newInfo);
//    return ResponseEntity.created(URI.create("/cultureinfo/early/")).build();
  }

  // 얼리버드 공연/전시 정보 수정
  @PutMapping("/early/{earlyCode}")
  public void modifyEarlyInfo(@PathVariable int earlyCode, @RequestBody CultureInfoDTO modifiedInfo) {
    cultureInfoService.modifyEarlyInfo(earlyCode, modifiedInfo);
  }

  // 얼리버드 공연/전시 정보 삭제
  @DeleteMapping("/early/{earlyCode}")
  public void deleteEarlyInfo(@PathVariable int earlyCode){
    cultureInfoService.deleteEarlyInfo(earlyCode);
  }
}
