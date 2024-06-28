package com.soop.pages.honeypot.model.dao;

import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HoneypotMapper {
    List<HoneypotDTO> getHoneypotList();

    HoneypotDTO getHoneypotByHoneypotCode(int honeypotCode);
}
