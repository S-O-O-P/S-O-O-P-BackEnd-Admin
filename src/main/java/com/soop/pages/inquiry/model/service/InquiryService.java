package com.soop.pages.inquiry.model.service;

import com.soop.pages.honeypot.model.dto.HoneypotDTO;
import com.soop.pages.inquiry.model.dto.InquiryDTO;
import com.soop.pages.inquiry.model.dao.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryMapper inquiryMapper;

    public List<InquiryDTO> getInquiryList() {

        List<InquiryDTO> inquiryList = inquiryMapper.getInquiryList();

        int displayOrder = inquiryList.size();
        for (InquiryDTO inquiry : inquiryList) {
            inquiry.setDisplayOrder(displayOrder--);
        }

        return inquiryList;
    }


    public InquiryDTO getInquiryByInquiryCode(int inquiryCode) {
        return inquiryMapper.getInquiryByInquiryCode(inquiryCode);
    }

    public boolean updateInquiryAnswer(int inquiryCode, String answer, Date answerDate) {
        int rowsUpdated = inquiryMapper.updateInquiryAnswer(inquiryCode, answer, answerDate);
        return rowsUpdated > 0;
    }

    public boolean updateInquiryStatus(int inquiryCode, String answerStatus) {
        int rowsUpdated = inquiryMapper.updateInquiryStatus(inquiryCode, answerStatus);
        return rowsUpdated > 0;
    }
}
