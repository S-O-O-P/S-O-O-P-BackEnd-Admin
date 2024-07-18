package com.soop.pages.customer.controller;

import com.soop.pages.customer.model.dto.LinkBeeUserDTO;
import com.soop.pages.customer.model.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "링크비 회원 정보", description = "링크비 가입 회원 정보를 관리하는 API입니다.")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "링크비 가입 회원 정보 전체 조회", description = "등록되어 있는 회원 전체 정보를 조회합니다.")
    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> getCustomerList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<LinkBeeUserDTO> usersInfo = customerService.getCustomerList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("usersInfo", usersInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }

    @Operation(summary = "링크비 가입 회원 상세 정보 조회", description = "등록되어 있는 회원 상세 정보를 조회합니다.")
    @Parameter(name = "userCode", description = "회원 코드인 userCode를 통해 해당 회원 상세 정보를 조회합니다.", in = ParameterIn.PATH)
    @GetMapping("/{userCode}")
    public ResponseEntity<LinkBeeUserDTO> getCustomerByUserCode(@PathVariable int userCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        LinkBeeUserDTO user = customerService.getCustomerByUserCode(userCode);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



//    @GetMapping("/{userCode}")
//    public ResponseEntity<LinkBeeUserDTO> getCustomerByUserCode(@PathVariable int userCode) {
//        try {
//            LinkBeeUserDTO user = customerService.getCustomerByUserCode(userCode);
//            if (user != null) {
//                return new ResponseEntity<>(user, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
