package com.soop.pages.cultureInfo.controller;

import com.soop.pages.cultureInfo.model.dto.CultureInfoDTO;
import com.soop.pages.cultureInfo.model.service.CultureInfoService;
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
@RequestMapping("/cultureinfo")
public class CultureInfoController {

  private final CultureInfoService cultureInfoService;

  public CultureInfoController(CultureInfoService cultureInfoService) {this.cultureInfoService = cultureInfoService;}

  // CultureInfo
  @GetMapping("/earlylist")
  public ResponseEntity<Map<String, Object>> getEarlyList() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    List<CultureInfoDTO> earlyBirdList = cultureInfoService.selectAllCultureInfo();
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("earlyBirdList", earlyBirdList);

    return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
  }
}
