package com.soop.pages.dashboard.model.dao;

import com.soop.pages.dashboard.model.dto.HoneypotDTO;
import com.soop.pages.dashboard.model.dto.InquiryDTO;
import com.soop.pages.dashboard.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {

    List<Map<String, Object>> getMonthlyHoneyCount();
    List<Map<String, Object>> getGenreHoneyCount();
    int getTodayMatchingCount();

    int getTotalMatchingCount();

    int getTodayInquiryCount();

    int getTotalInquiryCount();

    int getTodayHoneyCount();

    Integer getTotalReportCount();

    Integer getTodayReportCount();

    List<HoneypotDTO> getReportList();

    List<InquiryDTO> selectInquiryList();

    List<NoticeDTO> getLatestNotices();



}
