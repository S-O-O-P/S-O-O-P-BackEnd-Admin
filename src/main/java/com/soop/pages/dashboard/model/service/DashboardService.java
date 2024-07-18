package com.soop.pages.dashboard.model.service;

import com.soop.pages.dashboard.model.dao.DashboardMapper;
import com.soop.pages.dashboard.model.dto.HoneypotDTO;
import com.soop.pages.dashboard.model.dto.NoticeDTO;
import com.soop.pages.dashboard.model.dto.InquiryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    public List<Map<String, Object>> getMonthlyHoneyCount() {
        return dashboardMapper.getMonthlyHoneyCount();
    }
    public List<Map<String, Object>> getGenreHoneyCount() {
        return dashboardMapper.getGenreHoneyCount();
    }
    public int getTodayMatchingCount() {
        return dashboardMapper.getTodayMatchingCount();
    }

    public int getTotalMatchingCount() {
        return dashboardMapper.getTotalMatchingCount();

    }

    public int getTodayInquiryCount() {
        return dashboardMapper.getTodayInquiryCount();
    }

    public int getTotalInquiryCount() {
        return dashboardMapper.getTotalInquiryCount();
    }

    public int getTodayHoneyCount() {
        return dashboardMapper.getTodayHoneyCount();
    }


    public Integer getTotalReportCount() {
        return dashboardMapper.getTotalReportCount();
    }

    public Integer getTodayReportCount() {
        return dashboardMapper.getTodayReportCount();
    }


    public List<HoneypotDTO> getReportList() {

        List<HoneypotDTO> honeypotList = dashboardMapper.getReportList();

        // Assign display order sequentially
        int displayOrder = honeypotList.size();
        for (HoneypotDTO honeypot : honeypotList) {
            honeypot.setDisplayOrder(displayOrder--);
        }

        return honeypotList;
    }


    public List<InquiryDTO> getInquiryList() {

        List<InquiryDTO> inquiryList = dashboardMapper.selectInquiryList();

        int displayOrder = inquiryList.size();
        for (InquiryDTO inquiry : inquiryList) {
            inquiry.setDisplayOrder(displayOrder--);
        }

        return inquiryList;
    }

    public List<NoticeDTO> getLatestNotices() {

        List<NoticeDTO> noticeList = dashboardMapper.getLatestNotices();
        int displayOrder = noticeList.size();
        for (NoticeDTO notice : noticeList) {
            notice.setDisplayOrder(displayOrder--);
        }

        return noticeList;
    }

}
