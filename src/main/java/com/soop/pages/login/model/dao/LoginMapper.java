package com.soop.pages.login.model.dao;

import com.soop.pages.login.model.dto.LinkBeeUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
     LinkBeeUserDTO findByNicknameAndUserCode(@Param("nickname") String nickname, @Param("userCode") int userCode);
}
