package com.soop.pages.cultureInfo.model.dao;

import com.soop.pages.cultureInfo.model.dto.CultureInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CultureInfoMapper {
  List<CultureInfoDTO> selectAllCultureInfo(); // 얼리버드 공연/전시 정보 전체 조회

  CultureInfoDTO selectEarlyInfoByEarlyBirdCode(int earlyCode); // 얼리버드 공연/전시 상세 조회

  void registerNewEarlyInfo(CultureInfoDTO newInfo); // NEW 얼리버드 공연/전시 등록

  void modifyEarlyInfo(Map<String, Object> params); // 얼리버드 공연/전시 정보 수정

  void deleteEarlyInfo(int earlyCode); // 얼리버드 공연/전시 삭제
}
