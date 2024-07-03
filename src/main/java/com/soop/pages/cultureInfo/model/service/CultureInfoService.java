package com.soop.pages.cultureInfo.model.service;

import com.soop.pages.cultureInfo.model.dao.CultureInfoMapper;
import com.soop.pages.cultureInfo.model.dto.CultureInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CultureInfoService {
  private final CultureInfoMapper cultureInfoMapper;

  public CultureInfoService(CultureInfoMapper cultureInfoMapper) {this.cultureInfoMapper = cultureInfoMapper;}

  // 얼리버드 공연/전시 정보 전체 조회
  public List<CultureInfoDTO> selectAllCultureInfo() {return cultureInfoMapper.selectAllCultureInfo();}

  public CultureInfoDTO selectEarlyInfoByEarlyBirdCode(int earlyCode) {return cultureInfoMapper.selectEarlyInfoByEarlyBirdCode(earlyCode);
  }

  @Transactional
  public void registerNewEarlyInfo(CultureInfoDTO newInfo) {
    cultureInfoMapper.registerNewEarlyInfo(newInfo);
  }

  @Transactional
  public void modifyEarlyInfo(int earlyCode, CultureInfoDTO modifiedInfo) {
    Map<String, Object> params = new HashMap<>();
    params.put("earlyCode", earlyCode);
    params.put("modifiedInfo", modifiedInfo);
    cultureInfoMapper.modifyEarlyInfo(params);
  }

  @Transactional
  public void deleteEarlyInfo(int earlyCode) {
    cultureInfoMapper.deleteEarlyInfo(earlyCode);
  }
}
