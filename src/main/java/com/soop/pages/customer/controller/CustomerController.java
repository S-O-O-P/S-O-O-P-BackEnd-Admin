package com.soop.pages.customer.controller;

import com.soop.pages.customer.model.dto.LinkBeeUserDTO;
import com.soop.pages.customer.model.service.CustomerService;
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

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> getCustomerList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<LinkBeeUserDTO> usersInfo = customerService.getCustomerList();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("usersInfo", usersInfo);
        return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
    }


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
