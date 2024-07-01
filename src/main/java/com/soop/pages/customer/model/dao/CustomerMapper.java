package com.soop.pages.customer.model.dao;

import com.soop.pages.customer.model.dto.LinkBeeUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<LinkBeeUserDTO> getCustomerList();

    LinkBeeUserDTO getCustomerByUserCode(int userCode);
}
