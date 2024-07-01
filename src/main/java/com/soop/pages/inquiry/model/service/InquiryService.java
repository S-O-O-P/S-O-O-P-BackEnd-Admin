package com.soop.pages.inquiry.model.service;

import com.soop.pages.inquiry.model.dto.InquiryDTO;
import com.soop.pages.inquiry.model.dao.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryMapper inquiryMapper;

    public List<InquiryDTO> getInquiryList() {
        return inquiryMapper.getInquiryList();
    }

    public InquiryDTO getInquiryByInquiryCode(int inquiryCode) {
        return inquiryMapper.getInquiryByInquiryCode(inquiryCode);
    }

    public boolean updateInquiryAnswer(int inquiryCode, String answer) {
        int rowsUpdated = inquiryMapper.updateInquiryAnswer(inquiryCode, answer);
        return rowsUpdated > 0;
    }

    public boolean updateInquiryStatus(int inquiryCode, String answerStatus) {
        int rowsUpdated = inquiryMapper.updateInquiryStatus(inquiryCode, answerStatus);
        return rowsUpdated > 0;
    }
}
