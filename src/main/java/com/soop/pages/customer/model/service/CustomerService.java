package com.soop.pages.customer.model.service;

import com.soop.pages.customer.model.dao.CustomerMapper;
import com.soop.pages.customer.model.dto.LinkBeeUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<LinkBeeUserDTO> getCustomerList() {
        return customerMapper.getCustomerList();
    }

    public LinkBeeUserDTO getCustomerByUserCode(int userCode) {
        return customerMapper.getCustomerByUserCode(userCode);
    }
}
