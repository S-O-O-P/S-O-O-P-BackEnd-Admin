package com.soop.pages.honeypot.controller;

import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import com.soop.pages.honeypot.model.service.HoneypotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/honeypot")
public class HoneypotController {

    @Autowired
    private HoneypotService honeypotService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getHoneypotList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<HoneypotDTO> honeypotInfo = honeypotService.getHoneypotList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("honeypotInfo", honeypotInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


    @GetMapping("/{honeypotCode}")
    public ResponseEntity<HoneypotDTO> getHoneypotByHoneypotCode(@PathVariable int honeypotCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        HoneypotDTO honeypot = honeypotService.getHoneypotByHoneypotCode(honeypotCode);
        if (honeypot != null) {
            return new ResponseEntity<>(honeypot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{honeypotCode}/toggleStatus")
    public ResponseEntity<?> toggleStatus(@PathVariable int honeypotCode, @RequestBody Map<String, String> request) {
        String newStatus = request.get("newStatus");

        try {
            honeypotService.updateHoneypotStatus(honeypotCode, newStatus);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating status");
        }
    }
}

