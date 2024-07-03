package com.soop.pages.cultureInfo.model.service;

import com.soop.pages.cultureInfo.model.dao.CultureInfoMapper;
import com.soop.pages.cultureInfo.model.dto.CultureInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureInfoService {
  private final CultureInfoMapper cultureInfoMapper;

  public CultureInfoService(CultureInfoMapper cultureInfoMapper) {this.cultureInfoMapper = cultureInfoMapper;}

  // 얼리버드 공연/전시 정보 전체 조회
  public List<CultureInfoDTO> selectAllCultureInfo() {return cultureInfoMapper.selectAllCultureInfo();}
}
