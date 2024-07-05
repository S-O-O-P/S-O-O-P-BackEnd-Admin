package com.soop.pages.login.model.dao;

import com.soop.pages.login.model.dto.LoginAndLinkBeeUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {
     LoginAndLinkBeeUserDTO findByIdAndPassword(Map<String, Object> params);
}
