package com.soop.pages.cultureInfo.model.dao;

import com.soop.pages.cultureInfo.model.dto.CultureInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CultureInfoMapper {
  List<CultureInfoDTO> selectAllCultureInfo(); // 얼리버드 공연/전시 정보 전체 조회
}
