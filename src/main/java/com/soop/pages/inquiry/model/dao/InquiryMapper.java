package com.soop.pages.inquiry.model.dao;

import com.soop.pages.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface InquiryMapper {
    List<InquiryDTO> getInquiryList();

    InquiryDTO getInquiryByInquiryCode(int inquiryCode);

    int updateInquiryAnswer(@Param("inquiryCode") int inquiryCode, @Param("answer") String answer, @Param("answerDate") Date answerDate);

    int updateInquiryStatus(@Param("inquiryCode") int inquiryCode, @Param("answerStatus") String answerStatus);
}
